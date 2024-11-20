package nl.miwnn.se14.furkan.footballclubdemo.controller;

import nl.miwnn.se14.furkan.footballclubdemo.model.Color;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClubUser;
import nl.miwnn.se14.furkan.footballclubdemo.model.Footballer;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.*;
import nl.miwnn.se14.furkan.footballclubdemo.service.FootballClubUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
@Controller
public class InitializeController {
    private final FootballClubRepository footballClubRepository;
    private final TrophyRepository trophyRepository;
    private final ColorRepository colorRepository;
    private final FootballClubUserService footballClubUserService;
    private final FootballerRepository footballerRepository;

    public InitializeController(FootballClubRepository footballClubRepository,
                                TrophyRepository trophyRepository,
                                ColorRepository colorRepository,
                                FootballClubUserService footballClubUserService, FootballerRepository footballerRepository) {
        this.footballClubRepository = footballClubRepository;
        this.trophyRepository = trophyRepository;
        this.colorRepository = colorRepository;
        this.footballClubUserService = footballClubUserService;
        this.footballerRepository = footballerRepository;
    }

    @EventListener
    private void seed(ContextRefreshedEvent event) {
        if (colorRepository.findAll().isEmpty()) {
            initializeDB();
        }
    }

    private void initializeDB() {
        makeFootballClubUser("Furkan", "123");
        makeFootballClubUser("Ikra", "321");
        Footballer tadic = new Footballer("Tadic", "AML", "Fenerbahce");
        Footballer icardi = new Footballer("Icardi", "ST", "Galatasaray");
        Footballer muslera = new Footballer("Muslera", "GK", "Galatasaray");
        Footballer dzeko = new Footballer("Dzeko", "ST", "Fenerbahce");

        footballerRepository.save(tadic);
        footballerRepository.save(icardi);
        footballerRepository.save(muslera);
        footballerRepository.save(dzeko);

        Color red = new Color("red");
        Color blue = new Color("blue");
        Color yellow = new Color("yellow");
        Color green = new Color("green");

        colorRepository.save(red);
        colorRepository.save(blue);
        colorRepository.save(yellow);
        colorRepository.save(green);

        FootballClub fenerbahce = new FootballClub("Fenerbahce", 1907);
        FootballClub galatasaray = new FootballClub("Galatasaray", 1905);

        footballClubRepository.save(fenerbahce);
        footballClubRepository.save(galatasaray);

    }

    private void makeFootballClubUser(String username, String password) {
        FootballClubUser user = new FootballClubUser();
        user.setUsername(username);
        user.setPassword(password);
        footballClubUserService.save(user);
    }



}
