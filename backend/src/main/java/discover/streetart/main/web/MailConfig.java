package discover.streetart.main.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("mail.vps2364072.servdiscount-customer.com");
        mailSender.setPort(25);

        // here muss meine Gmail Discovery EmailAddresse hin
        mailSender.setUsername("info");
        mailSender.setPassword("info");


        //
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");



        return mailSender;
    }


}
