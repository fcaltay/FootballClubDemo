package nl.miwnn.se14.furkan.footballclubdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

/**
 * @author Furkan Altay
 * A club for which may have some players or others
 */
@Entity
/* @Entity
JPA is an API that facilitates the mapping of Java objects to relational database tables.
By using this annotation, the FootballClub class will be represented as a table in the database.
*/

public class FootballClub {
    @Id @GeneratedValue
    // The @Id annotation specifies that this field (in this case, clubId) is the primary key in the database.
    // Each football club will have a unique identifier.
    // The @GeneratedValue annotation indicates that the value of this field will be automatically generated.
    // When a new FootballClub instance is created, this field's value will be set automatically
    // by the database (typically as an incrementing number).
    private Long clubId;
    private String naam;
    private int foundingYear;

    @OneToMany(mappedBy = "footballClub")
    private List<Trophy> trophies;

    public int getNumberofATrophiesInTheMuseum() {
        int count = 0;

        for (Trophy trophy : trophies) {
            if (trophy.getAvailable() != null && trophy.getAvailable()) {
                count++;
            }
        }
        return count;
    }


    public int getNumberOfTrophies() {
        return trophies.size();
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }
}
