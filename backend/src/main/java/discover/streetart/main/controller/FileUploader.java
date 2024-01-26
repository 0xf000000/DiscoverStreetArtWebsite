package discover.streetart.main.controller;


import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Controller
public class FileUploader {



    // just simply gets the html page that gives us the file
    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    // we also should here validate that its a picture file and nothing else reallz important
    @PostMapping("api/v1/upload")
    public String uploadFile(  Model model,@RequestParam("image") MultipartFile file ) {

        try{
            // here we need to change the paths, when we deploy to my server
            // WE NEED TO ALSO POST A LOT OF OTHER STUFF HERE
            file.transferTo(new File("/Users/leon/code/DiscoverStreetArtWebsite/backend/src/main/resources/static/pictures/" + file.getOriginalFilename()));

        }catch(IOException e){
            model.addAttribute("msg", "there was an issue with uploading the images to the Server please try again :(");
            return "upload?success";
        }

        model.addAttribute("msg", "streetArt Succsessfully uploaded!");

        return "upload";
    }



}
