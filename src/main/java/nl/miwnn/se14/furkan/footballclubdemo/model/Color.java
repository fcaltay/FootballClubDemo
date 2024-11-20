package nl.miwnn.se14.furkan.footballclubdemo.model;

import jakarta.persistence.*;
import java.util.Set;

/**
 * Color entity representing a color used by football clubs.
 * This entity has a Many-to-Many self-referential relationship.
 */
@Entity
public class Color {

    @Id @GeneratedValue
    private Long colorId;

    @Column(unique = true, nullable = false)
    private String name;

    // Self-referential Many-to-Many relationship
    @ManyToMany(mappedBy = "colors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<FootballClub> footballClubs;
    //Burada hata veriyor

    // Constructors, Getters and Setters

    public Color() {}

    public Color(String name) {
        this.name = name;
    }

    public Long getColorId() {
        return colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<FootballClub> getFootballClubs() {
        return footballClubs;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }
    @PreRemove
    private void removeAssociationsWithFootballClubs() {
        for (FootballClub club : footballClubs) {
            club.getColors().remove(this);
        }
        footballClubs.clear();
    }
}
