package discover.streetart.main.security;

import discover.streetart.main.domain.User;
import discover.streetart.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       final String username = authentication.getName();
       final  String password = authentication.getCredentials().toString();

       User userDetails  = userService.getUserByUsername(username);

       if( userDetails == null) {
           throw new RuntimeException();
       }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
