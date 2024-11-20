package nl.miwnn.se14.furkan.footballclubdemo.service.mapper;

import nl.miwnn.se14.furkan.footballclubdemo.dto.FootballClubDTO;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClubUser;

/**
 * @author Furkan Altay
 * Converts between Model classes and DTOs
 */
public class FootballClubUserMapper {
    public static FootballClubUser fromDTO(FootballClubDTO dto) {
        FootballClubUser user = new FootballClubUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
