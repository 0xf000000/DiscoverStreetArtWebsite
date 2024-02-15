package discover.streetart.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
