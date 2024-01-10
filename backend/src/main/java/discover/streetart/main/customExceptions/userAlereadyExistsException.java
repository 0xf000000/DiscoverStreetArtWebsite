package discover.streetart.main.customExceptions;

import javax.naming.AuthenticationException;

public class userAlereadyExistsException extends AuthenticationException {

    public userAlereadyExistsException(String message){
        super(message);
    }


}
