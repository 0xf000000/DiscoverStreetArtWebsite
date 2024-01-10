package discover.streetart.main.preConfig;


import discover.streetart.main.domain.Comments;
import discover.streetart.main.domain.StreetArt;
import discover.streetart.main.domain.User;
import discover.streetart.main.repositery.CommentRepositery;
import discover.streetart.main.repositery.StreetArtRepositery;
import discover.streetart.main.repositery.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

/**
 * just a subclass for testing purposes the got some data in our application and a testuser
 */

@Configuration
public class LoadDataBase {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);


    /**
     * preloads some Data on start into the database for testing purposes
     * @param streetArtRepositery
     * @return
     */
    @Bean
    CommandLineRunner initDatabase(StreetArtRepositery streetArtRepositery, CommentRepositery commentRepositery, UserRepository userRepository){

        // password is set to 'test' its just in btcrypt hash form cause otherwise we can auth
        String passwordHash = "$2y$10$ujIHAAj0Q1I/Y9nSWV4Gr.obBcmFmXn0.snSBefbGeXq9G.62K/GG";


        return args -> {
            StreetArt artpoint1 = new StreetArt((float) 2000, "DKDKA", "base64","2023","john travolta", "description", new Timestamp(2023));
            log.info("Preloading: " + streetArtRepositery.save(artpoint1));
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 1290, "COLCOCL", "base64","2023","john travolta", "description", new Timestamp(2023))) );
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 8423, "COCLCOL", "base64","2023","john travolta", "description", new Timestamp(2023))) );
            log.info("Preloading: " + commentRepositery.save(new Comments("woow thats a really cool streetArt3", "john", artpoint1, new Timestamp(2023))));
            log.info("Preloading: " + commentRepositery.save(new Comments("sssssss", "john", artpoint1, new Timestamp(2023))));
            log.info("Preloading: " + userRepository.save(new User("test", passwordHash , "test@mail.com")) + "");
            log.info(userRepository.findUserByEmail("test@mail.com").getEmail());
            log.info("SETUP FINISHED TO SEE PLEASE VISIT 'http://localhost:8080/'");
        };
    }
}
