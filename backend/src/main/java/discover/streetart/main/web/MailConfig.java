package discover.streetart.main.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        // here muss meine Gmail Discovery EmailAddresse hin
        mailSender.setUsername("info.discoverstreetart@gmail.com");
        mailSender.setPassword("anjb guwv nmms njgq");


        //ok doesnt rl work rn sadly but mabey later on otherwise we wil just use a gmail address to make things way more easy
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");



        return mailSender;
    }


}
