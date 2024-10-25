package nl.miwnn.se14.furkan.footballclubdemo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

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
    private Long clubId;
    private String name;
    private Integer foundingYear;
    // The @Id annotation specifies that this field (in this case, clubId) is the primary key in the database.
    // Each football club will have a unique identifier.
    // The @GeneratedValue annotation indicates that the value of this field will be automatically generated.
    // When a new FootballClub instance is created, this field's value will be set automatically
    // by the database (typically as an incrementing number).
    @OneToMany(mappedBy = "footballClub", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Trophy> trophies;
    // private List<Trophy> trophies;
    //cascade = CascadeType.ALL means that any operation (e.g., save, delete, update) performed on the parent entity will also be applied to its related entities.
    // In this case, when a FootballClub is saved, all related Trophy entities are saved
    // and when a FootballClub is deleted, all related Trophy entities are also deleted.

    // orphanRemoval = true ensures that if a child entity (orphan entity) is removed from its parent entity's collection,
    // it is automatically deleted from the database. In this case, if a Trophy is removed from a FootballClub,
    // that trophy will be automatically deleted from the database.


    @ManyToMany
    private Set<Color> colors;

    public String getColorString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Color color : colors) {
            stringBuilder.append(color.getName()).append(" ");
        }
        return stringBuilder.toString();
    }


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(Integer foundingYear) {
        this.foundingYear = foundingYear;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public Set<Trophy> getTrophies() {
        return trophies;
    }

    public void setTrophies(Set<Trophy> trophies) {
        this.trophies = trophies;
    }
}
