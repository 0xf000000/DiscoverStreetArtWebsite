package discover.streetart.main.event;

import discover.streetart.main.domain.User;
import discover.streetart.main.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private IUserService userService;
    @Autowired
    private MessageSource messages;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {


    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.


    }


}
