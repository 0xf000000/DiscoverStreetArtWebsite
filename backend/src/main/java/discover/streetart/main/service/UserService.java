package discover.streetart.main.service;

import discover.streetart.main.customExceptions.userAlereadyExistsException;
import discover.streetart.main.domain.Role;
import discover.streetart.main.domain.User;
import discover.streetart.main.domain.VerifycationToken;
import discover.streetart.main.repositery.UserRepository;
import discover.streetart.main.repositery.VerifycationTokenRepositery;
import discover.streetart.main.security.SecurityConfiguration;
import discover.streetart.main.web.dto.UserRegestrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerifycationTokenRepositery tokenRepositery;

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

    /**
     * checks if we have a corresponding token in our database
     * @param token
     * @return
     */
    public VerifycationToken findByToken(String token){


       return tokenRepositery.findByToken(token);

    }

    /**
     * calculates expiryDate for the auth token to authenticate as a User
     * @param expiryTimeInMinutes Integer should pass it in minutes
     * @return
     */
    private Date calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());

    }


    // basic impl of save method i should make the validation client side?
    @Override
    public User RegisterNewAccount(UserRegestrationDto userRegestrationDto) throws userAlereadyExistsException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         User user = userRepository.findUserByEmail(userRegestrationDto.getEmail());

        if( user != null){
            final String errorMessage = "USER ALEREADY EXITS";
            throw new  userAlereadyExistsException(errorMessage);

        }

        User newUser = new User(userRegestrationDto.getUsername(), encoder.encode(userRegestrationDto.getPassword()), userRegestrationDto.getEmail() );

        return userRepository.save(newUser);
    }

    /**
     * saves a aleready registered User to the database with his confirmation activation
     * @param user
     * @return
     */
    public User saveRegisteredUser( User user){
        return userRepository.save(user);
    }

    @Override
    public void setAuthToken(User user, String token) {
        VerifycationToken newToken = new VerifycationToken();
        final int expiryDateInMinutes = 2880;
        final Date expiryDate = calculateExpiryDate(expiryDateInMinutes);
        newToken.setToken(token);
        newToken.setUser(user);
        newToken.setExpiryDate(expiryDate);

        tokenRepositery.save(newToken);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserbyemail(email);
        boolean enabled = user.isEnabled();
        if( user == null){
            throw new UsernameNotFoundException("username doesnt exists in database");
        }

        if(!enabled){

            throw new UsernameNotFoundException("please confirm your identity with your email adress");

        }


        List<Role> roles = Arrays.asList(user.getRole());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(Role.USER.toString())
                .build();



        return userDetails;
    }
}
