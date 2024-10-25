package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.model.Color;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.ColorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Furkan Altay
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
        model.addAttribute("newColor", new Color());

        return "colorOverview";
    }

    @PostMapping("/new")
    private String saveOrUpdateColor(@ModelAttribute("newColor") Color color, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("allColors", colorRepository.findAll());
            model.addAttribute("newColor", color);
            return "colorOverview";
        }

        try {
            colorRepository.save(color);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error saving color: " + e.getMessage());
            model.addAttribute("allColors", colorRepository.findAll());
            model.addAttribute("newColor", color);
            return "colorOverview";
        }
        return "redirect:/color/overview";

    }

}
