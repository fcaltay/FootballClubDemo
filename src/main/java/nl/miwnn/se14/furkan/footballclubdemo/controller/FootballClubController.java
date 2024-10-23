package nl.miwnn.se14.furkan.footballclubdemo.controller;

import jdk.jfr.Category;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Furkan Altay
 * Handle all request related primarly to clubs
 */

@Controller
public class FootballClubController {
    private final FootballClubRepository footballClubRepository;

    public FootballClubController(FootballClubRepository footballClubRepository) {
        this.footballClubRepository = footballClubRepository;
    }

    @GetMapping({"/", "/footballclub/overview"})
    private String showFootballClubOverview(Model datamodel) {
        datamodel.addAttribute("allFootballClubs", footballClubRepository.findAll());
        // It retrieves all football clubs from the database and adds them to the datamodel,
        // which will be used to render the footballClubOverview view.
        return "footballClubOverview";
    }

    @GetMapping("/footballclub/new")
    public String showFootballClubForm(Model datamodel) {
        datamodel.addAttribute("newFootballClub", new FootballClub());
        //  It creates a new instance of FootballClub, adds it to the datamodel, and returns the footballClubForm view,
        //  which displays a form for creating a new football club.
        return "footballClubForm";
    }

    @PostMapping("/footballclub/new")
    private String saveOrUpdateFootballClub(@ModelAttribute("newFootballClub") FootballClub footballClubToBeSaved, BindingResult result) {
        if (result.hasErrors()) {
            System.err.println(result.getAllErrors());
            // It takes the submitted FootballClub object from the form and checks for validation errors using BindingResult.
            // If there are errors, it redirects to the overview page; otherwise, it saves the new club to the database
            // and redirects to the overview page.
            return "redirect:/footballclub/overview";
        }

        footballClubRepository.save(footballClubToBeSaved);
        return "redirect:/footballclub/overview";
    }

    @GetMapping("/footballclub/delete/{clubId}")
    private String deleteBook(@PathVariable("clubId") Long clubId) {
        footballClubRepository.deleteById(clubId);
        // It retrieves the clubId from the URL and deletes the corresponding football club from the database,
        // then redirects back to the overview page.
        return "redirect:/footballclub/overview";
    }

}