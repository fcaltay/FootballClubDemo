package nl.miwnn.se14.furkan.footballclubdemo.controller;

import jakarta.transaction.Transactional;
import nl.miwnn.se14.furkan.footballclubdemo.model.Color;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.ColorRepository;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Handles all requests related primarily to colors.
 */
@Controller
@RequestMapping("/color")
public class ColorController {
    private final ColorRepository colorRepository;
    private final FootballClubRepository footballClubRepository;

    public ColorController(ColorRepository colorRepository, FootballClubRepository footballClubRepository) {
        this.colorRepository = colorRepository;
        this.footballClubRepository = footballClubRepository;
    }

    @GetMapping("/overview")
    private String showColorOverview(Model model) {
        model.addAttribute("allColors", colorRepository.findAll());
        model.addAttribute("formColor", new Color());

        return "colorOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateColor(@ModelAttribute("formColor") Color color, BindingResult result, Model model) {
        Optional<Color> sameName = colorRepository.findColorByName(color.getName());

        if (sameName.isPresent() && !sameName.get().getColorId().equals(color.getColorId())) {
            result.addError(new FieldError("formColor", "name", "There is already a color with this name"));
            model.addAttribute("allColors", colorRepository.findAll()); // Reload list if there are errors
            return "colorOverview"; // Revert page to show errors
        }

        // Ensure color.getColorId() is null for new colors
        color.setColorId(null); // This ensures a new color gets a new I
        // Add new or updated color record
        colorRepository.save(color);
        return "redirect:/color/overview"; // Redirect after saving
    }


    @GetMapping("/edit/{colorName}")
    public String editColor(@PathVariable String colorName, Model model) {
        // Rengi adıyla bul
        Optional<Color> colorToEdit = colorRepository.findColorByNameIgnoreCase(colorName);

        // Eğer renk mevcutsa, önce silme işlemini gerçekleştir
        if (colorToEdit.isPresent()) {
            Color color = colorToEdit.get();

            // Rengi sil
            colorRepository.delete(color);

            // Silme işleminden sonra yeni bir Color nesnesi oluştur ve modelde göster
            Color newColor = new Color();
            newColor.setName(colorName); // İstediğiniz yeni ismi ayarlayın
            model.addAttribute("formColor", newColor);

            // Başarı mesajı ekleyin
            model.addAttribute("success", "Color '" + colorName + "' has been deleted and is ready for editing.");
        } else {
            // Renk bulunamazsa hata mesajı ekleyip ana sayfaya yönlendirebiliriz
            model.addAttribute("error", "Color not found");
            return "redirect:/color/overview"; // Veya uygun bir hata sayfasına yönlendir
        }

        return "colorEdit"; // Düzenleme sayfasına yönlendir
    }




    @GetMapping("/detail/{colorName}")
    private String showColorDetailPage(@PathVariable("colorName") String colorName, Model model) {
        Optional<Color> color = colorRepository.findColorByName(colorName);

        if (color.isEmpty()) {
            return "redirect:/color/overview";
        }

        model.addAttribute("colorToBeShown", color.get());
        return "colorDetail";
    }

    @GetMapping("/delete/{colorName}")
    private String deleteColor(@PathVariable("colorName") String colorName, RedirectAttributes redirectAttributes) {
        Optional<Color> colorOptional = colorRepository.findColorByName(colorName);
        if (colorOptional.isPresent()) {
            Color color = colorOptional.get();

            // Remove the color from each associated football club
            for (FootballClub club : color.getFootballClubs()) {
                club.getColors().remove(color); // Remove the color from each football club
                // Note: You should save the club to persist the changes
                footballClubRepository.save(club);
            }

            // After all associations are removed, delete the color
            colorRepository.delete(color); // Now it's safe to delete the color
            redirectAttributes.addFlashAttribute("success", "Color deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Color not found");
        }
        return "redirect:/color/overview";
    }


}
