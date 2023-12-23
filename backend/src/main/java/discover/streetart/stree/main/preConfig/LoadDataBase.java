package discover.streetart.stree.main.preConfig;


import discover.streetart.stree.main.domain.StreetArt;
import discover.streetart.stree.main.repositery.StreetArtRepositery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class LoadDataBase {
    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);


    @Bean
    CommandLineRunner initDatabase(StreetArtRepositery streetArtRepositery){

        return args -> {
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 2000, "DKDKA", "base64","2023","john travolta", "description", new Timestamp(2023))) );
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 1290, "COLCOCL", "base64","2023","john travolta", "description", new Timestamp(2023))) );
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 8423, "COCLCOL", "base64","2023","john travolta", "description", new Timestamp(2023))) );
            log.info("Preloading: " + streetArtRepositery.save(new StreetArt((float) 2591, "another", "base64","2023","john travolta", "description", new Timestamp(2023))) );

        };

    }
}
