package nl.miwnn.se14.furkan.footballclubdemo.repositories;

import nl.miwnn.se14.furkan.footballclubdemo.dto.FootballClubDTO;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClubUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface FootballClubUserRepository extends JpaRepository<FootballClubUser, Long> {
    Optional<FootballClubUser> findByUsername(String username);

}
