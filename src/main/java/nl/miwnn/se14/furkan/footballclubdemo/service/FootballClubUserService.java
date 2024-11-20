package nl.miwnn.se14.furkan.footballclubdemo.service;

import jakarta.transaction.Transactional;
import nl.miwnn.se14.furkan.footballclubdemo.dto.FootballClubDTO;
import nl.miwnn.se14.furkan.footballclubdemo.model.FootballClubUser;
import nl.miwnn.se14.furkan.footballclubdemo.repositories.FootballClubUserRepository;
import nl.miwnn.se14.furkan.footballclubdemo.service.mapper.FootballClubUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Furkan Altay
 * Purpose for the class
 */
@Service
public class FootballClubUserService implements UserDetailsService {
   private final FootballClubUserRepository footballClubUserRepository;
   private final PasswordEncoder passwordEncoder;

    public FootballClubUserService(FootballClubUserRepository footballClubUserRepository, PasswordEncoder passwordEncoder) {
        this.footballClubUserRepository = footballClubUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FootballClubUser user = footballClubUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        // Şifreyi kontrol etmeden önce doğru şifreleme kullanıldığından emin olun.
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
    public boolean usernameInUse(String username) {
        return footballClubUserRepository.findByUsername(username).isPresent();
    }

    public void save(FootballClubDTO userDTO) {
        save(FootballClubUserMapper.fromDTO(userDTO));
    }

    public void save(FootballClubUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Şifreyi encode ederek kaydedin.
        footballClubUserRepository.save(user);
    }

    public List<FootballClubUser> getAllUsers() {
        return footballClubUserRepository.findAll();
    }
}
