package bg.softuni.books.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    //add OpenApi Docs http://localhost:8080/v3/api-docs
//    http://localhost:8080/swagger-ui-custom.html
    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo
                (new Info().
                        title("Our open book API").
                        version("1.0.0").
                        contact(new Contact().name("Magdalena Velikova").
                                email("magdalenal.velikova@gmail.com")).
                        description("Our book API"));
        return openAPI;

    }

}
