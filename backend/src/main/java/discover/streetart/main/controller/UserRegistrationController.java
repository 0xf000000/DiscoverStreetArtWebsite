package discover.streetart.main.controller;

import discover.streetart.main.service.UserService;
import discover.streetart.main.web.dto.UserRegestrationDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private UserService userService;

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


    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegestrationDto userRegestrationDto, HttpServletRequest httpServletRequest, Errors errors){

           userService.save(userRegestrationDto);



        return "redirect:/registration?success";
    }

}
