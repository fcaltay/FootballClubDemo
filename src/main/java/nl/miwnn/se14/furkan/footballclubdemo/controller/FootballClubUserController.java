package nl.miwnn.se14.furkan.footballclubdemo.controller;

import jakarta.validation.Valid;
import nl.miwnn.se14.furkan.footballclubdemo.dto.FootballClubDTO;
import nl.miwnn.se14.furkan.footballclubdemo.service.FootballClubUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class FootballClubUserController {
    private final FootballClubUserService footballClubUserService;

    public FootballClubUserController(FootballClubUserService footballClubUserService) {
        this.footballClubUserService = footballClubUserService;
    }

    @GetMapping("/overview")
    public String showUserOverview(Model model) {
        model.addAttribute("allUsers", footballClubUserService.getAllUsers());
        model.addAttribute("formUser", new FootballClubDTO());

        return "userOverview";
    }

    @PostMapping("/save")
    private String saveOrUpdateUser(@ModelAttribute("formUser") @Valid FootballClubDTO userDtoToBeSaved, BindingResult result,
                                    Model datamodel) {
        if (footballClubUserService.usernameInUse(userDtoToBeSaved.getUsername())) {
            result.rejectValue("username", "duplicate", "This username is not available");
        }

        if (!userDtoToBeSaved.getPassword().equals(userDtoToBeSaved.getPasswordConfirm())) {
            result.rejectValue("password", "no.match", "The passwords do not match");
        }

        if (result.hasErrors()) {
            datamodel.addAttribute("allUsers", footballClubUserService.getAllUsers());
            datamodel.addAttribute("formModalHidden", false);
            return "userOverview";
        }

        footballClubUserService.save(userDtoToBeSaved);
        return "redirect:/user/overview";
    }

}
