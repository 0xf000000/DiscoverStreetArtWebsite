package discover.streetart.stree.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    // returns Index Page
    @RequestMapping("/")
    public String home(){
        return "index";
    }
    // upload Page
    @RequestMapping("/Upload")
    public String upload(){
        return "upload";
    }
    //map Page
    @RequestMapping("/Map")
    public String map(){ return "map"; }


}
