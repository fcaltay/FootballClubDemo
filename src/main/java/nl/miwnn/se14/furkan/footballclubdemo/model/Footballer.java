package nl.miwnn.se14.furkan.footballclubdemo.model;

import jakarta.persistence.*;

import java.util.Set;

/**
 * @author Furkan Altay
 * Purpose for the class
 */

@Entity
public class Footballer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long footballerId;

    private String imageUrl;

    @Column(unique = true, nullable = false)
    private String footballerName;
    private String footballerPosition;
    private String team;



    @ManyToMany(mappedBy = "footballers")  // footballers burada mappedBy
    private Set<FootballClub> footballClubs;



    public Footballer(String footballerName, String footballerPosition,String team) {
        this.footballerName = footballerName;
        this.footballerPosition = footballerPosition;
        this.team = team;
    }

    public Footballer() {}

    public long getFootballerId() {
        return footballerId;
    }

    public void setFootballerId(long footballerId) {
        this.footballerId = footballerId;
    }

    public String getFootballerName() {
        return footballerName;
    }

    public void setFootballerName(String footballerName) {
        this.footballerName = footballerName;
    }

    public String getFootballerPosition() {
        return footballerPosition;
    }

    public void setFootballerPosition(String footballerPosition) {
        this.footballerPosition = footballerPosition;
    }

    public Set<FootballClub> getFootballClubs() {
        return footballClubs;
    }

    public void setFootballClubs(Set<FootballClub> footballClubs) {
        this.footballClubs = footballClubs;
    }
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
