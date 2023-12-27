package discover.streetart.main.service;

import discover.streetart.main.domain.User;
import discover.streetart.main.repositery.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserByID(Long id){
        return userRepository.findById(id);

    }


    public User getUserbyemail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User getUserByUsername(String username){
        return userRepository.findUserByUsername(username);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        List<String> roles = Arrays.asList(user.getRole());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();



        return userDetails;
    }
}
