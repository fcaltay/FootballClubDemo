package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.model.Trophy;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubRepository;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.TrophyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author Furkan Altay
 * Handle all request related primarly to trophy
 */
@Controller
public class TrophyController {
    private final FootballClubRepository footballClubRepository;
    private final TrophyRepository trophyRepository;


    public TrophyController(FootballClubRepository footballClubRepository, TrophyRepository trophyRepository) {
        this.footballClubRepository = footballClubRepository;
        this.trophyRepository = trophyRepository;
    }

    @GetMapping("/trophy/new/{clubId}")
    private  String createNewTrophy(@PathVariable("clubId") Long clubId) {
        Optional<FootballClub> footballClubOptional = footballClubRepository.findById(clubId);

        if (footballClubOptional.isEmpty()) {
            System.err.printf("Could not retrieve football club with ID: %d\n", clubId);
            return "redirect:/";
        }
        Trophy trophy = new Trophy();
        trophy.setFootballClub(footballClubOptional.get());
        trophyRepository.save(trophy);
        return "redirect:/";

    }
}
