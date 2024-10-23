package nl.miwnn.se14.furkan.footballclubdemo.repositories;

import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClub;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
public interface FootballClubRepository extends JpaRepository <FootballClub, Long> {
}
/*
By extending JpaRepository<FootballClub, Long>, the FootballClubRepository inherits several methods for performing
CRUD (Create, Read, Update, Delete) operations on FootballClub entities without needing to implement these methods manually.
JpaRepository provides methods such as save(), findAll(), findById(), deleteById(),
and many others that allow developers to interact with the database easily.

The FootballClubRepository interface serves as a bridge between the application and the database,
enabling easy data manipulation for football clubs.
 */