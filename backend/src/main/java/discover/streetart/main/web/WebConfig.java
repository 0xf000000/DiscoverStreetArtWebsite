package discover.streetart.main.web;

import discover.streetart.main.StreeApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/static/pictures/", "file:" + StreeApplication.IMAGE_DIR
    };

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**", "static/pictures/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
