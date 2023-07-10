package bg.softuni.books.config;


import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Contact;
import io.swagger.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  @Bean
  public OpenAPI openAPI() {
    OpenAPI info = new OpenAPI().
            info(new Info().
                    title("Our open book API").
                    version("1.0.0").
                    contact(new Contact().name("Lachezar Balev").
                            email("lachezar.balev@gmail.com")).
                    description("Our book API"));
    return info;
  }

}
