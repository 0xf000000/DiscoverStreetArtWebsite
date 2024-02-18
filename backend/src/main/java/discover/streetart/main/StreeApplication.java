package discover.streetart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class StreeApplication extends SpringBootServletInitializer {
  
	private Logger logger = LoggerFactory.getLogger(StreeApplication.class);
	public static String IMAGE_DIR;
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StreeApplication.class);
	}


	public static void main(String[] args) {
		IMAGE_DIR = "/Users/leon/code/DiscoverStreetArtWebsite/backend/src/main/resources/static/pictures";
		SpringApplication.run(StreeApplication.class, args);
	}

}
