package nl.miwnn.se14.furkan.footballclubdemo.repositories;

import nl.miwnn.se14.furkan.footballclubdemo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Optional<Color> findColorByName(String name);
}
