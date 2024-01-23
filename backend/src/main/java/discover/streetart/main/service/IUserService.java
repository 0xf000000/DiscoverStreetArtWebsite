package discover.streetart.main.service;

import discover.streetart.main.customExceptions.userAlereadyExistsException;
import discover.streetart.main.domain.User;
import discover.streetart.main.web.dto.UserRegestrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService {
    User RegisterNewAccount(UserRegestrationDto userRegestrationDto) throws userAlereadyExistsException;

    void setAuthToken( User user, String token);


}
