package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.repositories.ColorRepository;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubRepository;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.TrophyRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public class InitializeController {
    private final FootballClubRepository footballClubRepository;
    private final TrophyRepository trophyRepository;
    private final ColorRepository colorRepository;

    public InitializeController(FootballClubRepository footballClubRepository, TrophyRepository trophyRepository, ColorRepository colorRepository) {
        this.footballClubRepository = footballClubRepository;
        this.trophyRepository = trophyRepository;
        this.colorRepository = colorRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent event) {
        if (colorRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
    }


}
