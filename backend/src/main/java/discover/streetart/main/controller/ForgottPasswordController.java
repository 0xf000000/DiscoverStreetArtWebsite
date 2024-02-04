package discover.streetart.main.controller;


import discover.streetart.main.domain.PasswordResetToken;
import discover.streetart.main.domain.User;
import discover.streetart.main.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/forgotPassword")
public class ForgottPasswordController {

    @Autowired
    IUserService userService;


    @GetMapping
    public String GET_PASSWORD_TEMPLATE(){

        return "forgotPassword";
    }

    @PostMapping()
    public String forgottPassword(@RequestParam(name = "email") String email ){

        UserDetails user = userService.loadUserByUsername(email);

        if( user == null){
            return "redirect:/forgotPassword?emailDontExist";
        }
        //PasswordResetToken passwordResetToken = new PasswordResetToken();



        return null;
    }
}
