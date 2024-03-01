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

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.Iterator;
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
            // check contentType
           String contentType =  file.getContentType();
           if(!(contentType.equals("image/jpeg") || contentType.equals("image/png"))){
               return new ResponseEntity<>("uploaded File is not an Image", HttpStatus.BAD_REQUEST);
           }
           // check file ending
           if( !(file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".png"))){
               return new ResponseEntity<>("uploaded File is not an Image", HttpStatus.BAD_REQUEST);
           }

           String fileName = encodeFileName(file.getOriginalFilename());
            File imageFile = new File(PICTURE_DIR + fileName);

            file.transferTo(imageFile);

            String fileExtension = getFileExtension(fileName);
            BufferedImage inputImage = ImageIO.read(imageFile);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(fileExtension);
            ImageWriter writer = writers.next();



        }catch(IOException exception){
            System.out.println(exception.fillInStackTrace());
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
        String CRYPTET_STRING = filename.replaceAll("\\s+","")
                .replace(";", "");

        String paths[] = CRYPTET_STRING.split("\\.");
        final int LENGTH  = paths.length -1 ;
        StringBuilder builder = new StringBuilder();
        // we dont know how many . there are in the string we only know that the last split is the file ending
        for( int i = 0; i < LENGTH; i++){
            if( i != LENGTH){
                builder.append(paths[i]);
            }
        }

        String base64EndcodedFileName = Base64.getEncoder().encodeToString(builder.toString().getBytes());

        String FINAL_FILENAME = base64EndcodedFileName + "." + paths[LENGTH];


       return FINAL_FILENAME;
    }

    // we need the bare file extension in order to compress the image i got the feeling this website is very slow without it
    String getFileExtension(String file){
        String fileNameSplit [] = file.split(".");
        int F_Length = fileNameSplit.length -1;
        String fileExtension = null;

        if( fileNameSplit[F_Length].equals(".jpg")) { fileExtension = fileNameSplit[F_Length];}
        if( fileNameSplit[F_Length].equals(".png")) { fileExtension = fileNameSplit[F_Length];}
       return fileExtension.replace(".","");
    }



}
