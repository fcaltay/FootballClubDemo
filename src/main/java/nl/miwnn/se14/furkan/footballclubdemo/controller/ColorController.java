package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.model.Color;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.ColorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Handles all requests related primarily to colors.
 */
@Controller
@RequestMapping("/color")
public class ColorController {
    private final ColorRepository colorRepository;

    public ColorController(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
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
        }

        if (result.hasErrors()) {
            model.addAttribute("allColors", colorRepository.findAll()); // Ensure all colors are available
            return "colorOverview"; // Return to the view to show errors
        }

        colorRepository.save(color);
        return "redirect:/color/overview"; // Redirect after save
    }


    @GetMapping("/edit/{colorName}")
    public String editColor(@PathVariable String colorName, Model model) {
        Optional<Color> color = colorRepository.findColorByName(colorName);

        if (color.isPresent()) {
            model.addAttribute("formColor", color.get());
        } else {

        }

        return "colorEdit";
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
    private String deleteColor(@PathVariable("colorName") String colorName) {
        colorRepository.findColorByName(colorName).ifPresent(colorRepository::delete);
        return "redirect:/color/overview";
    }
}
