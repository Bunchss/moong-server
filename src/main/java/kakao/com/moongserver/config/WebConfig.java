package kakao.com.moongserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String FRONT_LOCAL_ADDRESS = "http://localhost:5173";
    private static final String FRONT_ADDRESS = "https://d3cf4a27v2oj60.cloudfront.net/";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(FRONT_LOCAL_ADDRESS, FRONT_ADDRESS)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .exposedHeaders("location")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
