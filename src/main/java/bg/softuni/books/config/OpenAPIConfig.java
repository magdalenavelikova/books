package bg.softuni.books.config;


import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.info.Contact;
import io.swagger.oas.models.info.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenAPIConfig {


  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().
            info(new Info().
                    title("Our open book API").
                    version("1.0.0").
                    contact(new Contact().name("Magdalena Velikova").
                            email("magdalenal.velikova@gmail.com")).
                    description("Our book API"));

  }

}
