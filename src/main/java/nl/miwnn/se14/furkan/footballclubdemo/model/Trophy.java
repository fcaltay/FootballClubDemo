package nl.miwnn.se14.furkan.footballclubdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * @author Furkan Altay
 * thrppy of a football ckub
 */
@Entity
public class Trophy {
    @Id @GeneratedValue
    private Long trophyId;

    private Boolean available;

    public Trophy() {
        this.available = true;
    }

    @ManyToOne
    private FootballClub footballClub;

    public Long getTrophyId() {
        return trophyId;
    }

    public void setTrophyId(Long trophyId) {
        this.trophyId = trophyId;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public FootballClub getFootballClub() {
        return footballClub;
    }

    public void setFootballClub(FootballClub footballClub) {
        this.footballClub = footballClub;
    }
}
