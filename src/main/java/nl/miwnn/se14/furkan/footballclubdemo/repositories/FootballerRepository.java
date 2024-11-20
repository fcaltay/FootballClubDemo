package nl.miwnn.se14.furkan.footballclubdemo.repositories;

import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import nl.miwnn.se14.furkan.footballclubdemo.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface FootballerRepository extends JpaRepository <Footballer, Long> {
}
