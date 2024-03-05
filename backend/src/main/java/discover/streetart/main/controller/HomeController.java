package discover.streetart.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class HomeController {

    // returns Index Page
    @GetMapping("/")
    public String home(){
        return "index";
    }
    // upload Page

    // just simply gets the html page that gives us the file
    @GetMapping("/uploadImage")
    public String Imageupload(){
        return "ImageUpload";
    }

    @GetMapping("/pointUpload")
    public String PointUpload() {return "streetArtUpload";}


    @GetMapping("/login")
    public String login() {return "login";}

    //map Page
    @GetMapping("/map")
    public String map(){ return "map"; }

    
    @GetMapping("/singlePage")
    public String singlePage(@RequestParam("artId") Optional<Integer> artId){
        if(!artId.isPresent()){
            return "redirect:/"; // were redirecting to the index page if we dont have request parameters
        }

        return "singlePage";}

}
