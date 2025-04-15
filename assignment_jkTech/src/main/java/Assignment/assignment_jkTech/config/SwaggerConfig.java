package Assignment.assignment_jkTech.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Document Ingestion and Q&A API")
                .version("1.0.0")
                .description("API for uploading, searching and filtering documents with keyword-based Q&A"));
    }
}
