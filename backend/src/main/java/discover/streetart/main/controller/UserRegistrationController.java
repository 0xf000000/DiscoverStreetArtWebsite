package discover.streetart.main.controller;

import discover.streetart.main.customExceptions.userAlereadyExistsException;
import discover.streetart.main.domain.User;
import discover.streetart.main.event.OnRegistrationCompleteEvent;
import discover.streetart.main.service.UserService;
import discover.streetart.main.web.dto.UserRegestrationDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
public String registrationCompleteEvent( @PathVariable String token){
        System.out.println(token);

        return "registration";

}


    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegestrationDto userRegestrationDto, HttpServletRequest httpServletRequest, Errors errors){
            try{
                String appUrl = httpServletRequest.getContextPath();

                User user = userService.save(userRegestrationDto);

                eventPublisher.publishEvent( new OnRegistrationCompleteEvent( appUrl, httpServletRequest.getLocale(), user));


            }catch(userAlereadyExistsException | RuntimeException e ){
                return "redirect:registration?error";
            }
            return "redirect:registration?success";
    }

}
