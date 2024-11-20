package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.model.Footballer;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubRepository;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Furkan Altay
 * Purpose for the class
 */

@Controller
@RequestMapping("/footballer")
public class FootballerController {
    private final FootballerRepository footballerRepository;


    public FootballerController(FootballerRepository footballerRepository) {
        this.footballerRepository = footballerRepository;

    }



    @GetMapping("/overview")
    public String listFootballers(Model model) {
        List<Footballer> allFootballers = footballerRepository.findAll();  // This should work now
        model.addAttribute("allFootballers", allFootballers);
        return "FootballerOverview";
    }

    @GetMapping("/new")
    public String showFootballerForm(Model datamodel) {
        datamodel.addAttribute("newFootballer", new Footballer());
        //  It creates a new instance of FootballClub, adds it to the datamodel, and returns the footballClubForm view,
        //  which displays a form for creating a new football club.
        return "footballerForm";
    }

    @PostMapping("/new")
    public String saveFootballer(@ModelAttribute("newFootballer") Footballer footballer) {
        footballerRepository.save(footballer);
        return "redirect:/footballer/overview";
    }

}
