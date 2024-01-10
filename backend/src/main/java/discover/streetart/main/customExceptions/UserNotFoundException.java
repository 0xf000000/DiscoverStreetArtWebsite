package discover.streetart.main.customExceptions;

import javax.naming.AuthenticationException;

/**
 * UserNotFoundException just gives us the the info, that we could not resolve the user with the provided username
 */

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String ERROR_MESSAGE){
        super(ERROR_MESSAGE);
    }


}
