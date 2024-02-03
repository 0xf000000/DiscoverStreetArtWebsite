package discover.streetart.main.controller;

import discover.streetart.main.customExceptions.userAlereadyExistsException;
import discover.streetart.main.domain.User;
import discover.streetart.main.domain.VerifycationToken;
import discover.streetart.main.event.OnRegistrationCompleteEvent;
import discover.streetart.main.service.UserService;
import discover.streetart.main.web.dto.UserRegestrationDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;



    @Autowired
    ApplicationEventPublisher eventPublisher;
    public UserRegistrationController(){
        super();
    }

    @ModelAttribute("user")
    public UserRegestrationDto userRegistrationDto(){
        return new UserRegestrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }


@GetMapping("/confirm" )
public String registrationCompleteEvent( @RequestParam("token") String token, WebRequest request){
        Locale locale = request.getLocale();
        VerifycationToken requestToken =  userService.findByToken(token);
         Calendar cal  = Calendar.getInstance();
        // if token doesnt exist in database, we just return a error page when u try to visit this endpoint
        if(requestToken == null){

            return "registration?error";
        }

       User user =  requestToken.getUser();
        Long expiredTimeInSeconds = requestToken.getExpiryDate().getTime() - cal.getTime().getTime();

        // if token is expired return error
        if( expiredTimeInSeconds <= 0 ) {
            return "confirm?error=tokenIsSadlyExpired";
        }



        user.setEnabled(true);

        userService.saveRegisteredUser(user);



        return "login";

}


    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegestrationDto userRegestrationDto, HttpServletRequest httpServletRequest, Errors errors){
            try{
                String appUrl = httpServletRequest.getContextPath();

                User user = userService.RegisterNewAccount(userRegestrationDto);
                eventPublisher.publishEvent( new OnRegistrationCompleteEvent( appUrl, httpServletRequest.getLocale(), user));


            }catch(userAlereadyExistsException | RuntimeException e ){
                System.out.println(e.fillInStackTrace());
                return "redirect:registration?error";
            }
            return "redirect:login?confirm";
    }

}
