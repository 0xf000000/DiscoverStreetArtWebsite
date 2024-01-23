package discover.streetart.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class StreeApplication {
  
	private Logger logger = LoggerFactory.getLogger(StreeApplication.class);
  
	public static void main(String[] args) {
		SpringApplication.run(StreeApplication.class, args);
	}

}
