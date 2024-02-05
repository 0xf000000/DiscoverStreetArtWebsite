package discover.streetart.main.event;


import discover.streetart.main.domain.User;
import discover.streetart.main.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ForgottPasswordListener implements ApplicationListener<OnPasswordResetEvent> {

    @Autowired
    private IUserService userService;

    @Autowired
    JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnPasswordResetEvent event) {
        this.confirmPasswordReset(event);
    }


    public void confirmPasswordReset(OnPasswordResetEvent event){
        User user = event.getUser();
        String appurl = event.getAppUrl();
        String token = UUID.randomUUID().toString();
        userService.setResetPasswordToken(user, token);

        mailSender.send(constructResetTokenEmail(user, appurl, token));




    }

    private SimpleMailMessage constructResetTokenEmail(User user, String appUrl, String token){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Hello dear User :0,\n\n");
        stringBuilder.append("please visit the following URL to reset your password!\n");
        String message =  appUrl + "/changePassword?token=" + token;
        stringBuilder.append(message);


        return constructMail( "Reset Password mail Discover StreetArt", stringBuilder.toString() ,user);
    }

    private SimpleMailMessage constructMail(String subject, String body, User user){

        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setTo(user.getEmail());
        email.setText(body);

        email.setFrom("discoverStreetArt");
        return email;
    }







}
