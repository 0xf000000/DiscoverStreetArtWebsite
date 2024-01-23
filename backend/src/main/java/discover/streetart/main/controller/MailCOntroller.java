package discover.streetart.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MailCOntroller {

    @Autowired
    JavaMailSender sender;

    // is broken rn some TLS error with the smtp server im not sure how to fix it rn
    @RequestMapping("/mail")
    public String sendMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();


        simpleMailMessage.setTo("leon@margale.de");
        simpleMailMessage.setText("hello my friend");


        sender.send(simpleMailMessage);

        return "hoh";
    }
}
