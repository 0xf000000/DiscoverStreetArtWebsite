package discover.streetart.main.controller;


import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

final String PICTURE_DIR = "/Users/leon/code/DiscoverStreetArtWebsite/backend/src/main/resources/static/pictures/";



    // we also should here validate that its a picture file and nothing else reallz important
    @PostMapping("api/v1/upload")
    public ResponseEntity<String> uploadFile(  @RequestParam("image") MultipartFile file ) {

        try{
            // here we need to change the paths, when we deploy to my server
            // small checkup so we validate this is an immage file
           String contentType =  file.getContentType();
           if(!(contentType.equals("image/jpeg") || contentType.equals("image/png"))){
               return new ResponseEntity<>("uploaded File is not an Image", HttpStatus.BAD_REQUEST);
           }

            file.transferTo(new File( PICTURE_DIR + file.getOriginalFilename()));

        }catch(IOException e){
            return new ResponseEntity<>("something went wrong server Internally", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("image got successfully uploaded! fileName: " + file.getOriginalFilename(), HttpStatus.OK);
    }



}
