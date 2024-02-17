package discover.streetart.main.controller;



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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

@Controller
public class FileUploader {

final String PICTURE_DIR = "/Users/leon/code/DiscoverStreetArtWebsite/backend/src/main/resources/static/pictures/";
// absolute path for the webserver to store the pictures
final String PICTURE_DIR_WIN = "C:\\Users\\GingerBeethoven\\code\\DiscoverStreetArtWebsite\\backend\\src\\main\\resources\\static\\pictures\\";


    // we also should here validate that its a picture file and nothing else reallz important
    @PostMapping("api/v1/upload")
    public ResponseEntity<String> uploadFile(  @RequestParam("image") MultipartFile file ) {

        try{
            // here we need to change the paths, when we deploy to my server
            // small checkup so we validate this is an immage file
           String contentType =  file.getContentType();
           if(!(contentType.equals("image/jpeg") || contentType.equals("image/png")) && !file.getOriginalFilename().endsWith(".png") && !file.getOriginalFilename().endsWith(".jpg") ){
               return new ResponseEntity<>("uploaded File is not an Image", HttpStatus.BAD_REQUEST);
           }

           String fileName = encodeFileName(file.getOriginalFilename());
           // holly shit cause im on windows i need to change it temporarly
            file.transferTo(new File( PICTURE_DIR + fileName));

        }catch(IOException e){
            System.out.println(e.fillInStackTrace());
            return new ResponseEntity<>("something went wrong server Internally", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(file.getOriginalFilename(), HttpStatus.OK);
    }


    /**
     * code duplication this method is only here to encode the filename
     * @param filename
     * @return
     */
    private String encodeFileName(String filename){
        String CRYPTET_STRING = filename.replaceAll("\\s+","");
        CRYPTET_STRING = CRYPTET_STRING.replace(";", "");
       CRYPTET_STRING =  URLEncoder.encode(CRYPTET_STRING, StandardCharsets.UTF_8);


       return CRYPTET_STRING;
    }



}
