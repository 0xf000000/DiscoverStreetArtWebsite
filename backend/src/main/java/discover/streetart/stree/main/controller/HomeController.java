package discover.streetart.stree.main.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @RequestMapping("/Upload")
    public String upload(){
        return "upload";
    }
    @RequestMapping("/Map")
    public String map(){ return "map"; }


}
