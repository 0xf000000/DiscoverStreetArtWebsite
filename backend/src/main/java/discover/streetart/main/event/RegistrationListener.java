package discover.streetart.main.event;

import discover.streetart.main.domain.User;
import discover.streetart.main.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Properties;
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
        this.confirmRegistration(event);

    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){


        //
        StringBuilder stringBuilder = new StringBuilder();
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.setAuthToken(user, token);

        // building the email user should get
        // this is messy would create a extra method
        final String RECIVER_ADRESS = user.getEmail();
        final String subject = "Registration Conformation DiscoverStreetArt";
        final String REGISTRATION_ROUTE = "/registration/confirm?token=";
        stringBuilder.append(event.getAppUrl());
        stringBuilder.append(REGISTRATION_ROUTE);
        stringBuilder.append(token);
        String confirmationUrl = stringBuilder.toString();


     //   userService. setUsertoken(User, token)
       // String message = messages.getMessage("message.regSucc", null, event.getLocale());
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(RECIVER_ADRESS);
        email.setSubject(subject);
        email.setText(" Hello new User, \n please visit the following link to verify your email address " + "\r\n" + "https://discover-streetart.de" + confirmationUrl);
        mailSender.send(email);




    }


}
