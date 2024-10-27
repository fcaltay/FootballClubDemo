package nl.miwnn.se14.furkan.footballclubdemo.model;

import com.mysql.cj.jdbc.jmx.LoadBalanceConnectionGroupManager;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

/**
 * @author Furkan Altay
 * Color of a football club
 */
@Entity
public class Color {

    @Id @GeneratedValue
    private Long colorId;

    // @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToMany
    private Set<Color> colors;

    public Long getColorId() {
        return colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Color> getColors() {
        return colors;
    }
}
