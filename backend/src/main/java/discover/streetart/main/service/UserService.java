package discover.streetart.main.service;

import discover.streetart.main.customExceptions.userAlereadyExistsException;
import discover.streetart.main.domain.Role;
import discover.streetart.main.domain.User;
import discover.streetart.main.repositery.UserRepository;
import discover.streetart.main.security.SecurityConfiguration;
import discover.streetart.main.web.dto.UserRegestrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;



    public Optional<User> getUserByID(Long id){
        return userRepository.findById(id);

    }


    public User getUserbyemail(String email){

        if(email == null|| email.equals("")){
            return null;
        }

        return userRepository.findUserByEmail(email);
    }

    public User getUserByUsername(String username){

        if ( username == null || username.equals("")){
            return null;
        }

        return userRepository.findUserByUsername(username);

    }


    // basic impl of save method i should make the validation client side?
    @Override
    public User save(UserRegestrationDto userRegestrationDto) throws userAlereadyExistsException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         User user = userRepository.findUserByEmail(userRegestrationDto.getEmail());

        if( user != null){
            final String errorMessage = "USER ALEREADY EXITS";
            throw new  userAlereadyExistsException(errorMessage);

        }

        User newUser = new User(userRegestrationDto.getUsername(), encoder.encode(userRegestrationDto.getPassword()), userRegestrationDto.getEmail() );

        return userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserbyemail(email);
        List<Role> roles = Arrays.asList(user.getRole());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(Role.USER.toString())
                .build();



        return userDetails;
    }
}
