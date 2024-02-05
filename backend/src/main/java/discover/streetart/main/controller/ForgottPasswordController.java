package discover.streetart.main.controller;


import discover.streetart.main.domain.PasswordResetToken;
import discover.streetart.main.domain.User;
import discover.streetart.main.event.OnPasswordResetEvent;
import discover.streetart.main.service.IUserService;
import discover.streetart.main.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;

@RequestMapping("/forgotPassword")
@Controller
public class ForgottPasswordController {

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    UserService userService;


    @GetMapping()
    public String showForgotPasswordForm(){

        return "forgotPassword";
    }

    @PostMapping()
    public ResponseEntity<String> forgottPassword(@RequestParam(name = "email") String email, HttpServletRequest request){

        User user =  userService.getUserbyemail(email);


        // if i throw an error here or display one someone could try to find out if a specific email exists in my database
        // so we just display a succsess page small hack but i think when i wanna show this online its way better to do it like this
        if( user == null){
          return new ResponseEntity<>( "email Sended", HttpStatus.OK);
        }

        try{

            // here should get an event fired
            eventPublisher.publishEvent(new OnPasswordResetEvent(user, request.getRequestURL().toString()));

        } catch(RuntimeException e){
            e.printStackTrace();
        }



        return new ResponseEntity<>("email Sended", HttpStatus.OK);
    }


    @GetMapping("/changePassword")
    public String changePassword( @RequestParam String token, HttpServletRequest httpServletRequest){
        PasswordResetToken resetToken = userService.findResetTokenByToken(token);

        if ( resetToken == null ){
            return "redirect:/login?error";
        }

        Calendar cal = Calendar.getInstance();
        Long expiredTimeInSeconds = resetToken.getExpiryDATE().getTime() - cal.getTime().getTime();

        if( expiredTimeInSeconds <= 0 ){
            return "redirect:/login?expired";
        }

        return "redirect:/forgotPassword/updatePassword?token=" + token;
    }


    @GetMapping("/updatePassword")
    public String updatePassword(@RequestParam String token){

        return "updatePassword";
    }
}
