package com.sitas.checkin.utils.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration class for OpenAPI documentation of the airline API.
 * This class defines a method to configure OpenAPI documentation for the API endpoints.
 */
@Configuration
public class OpenAPIConfig {

    @Value("${airline-api.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("airline@udea.edu.co");
        contact.setName("CheckIn");
        contact.setUrl("https://www.airline.com");

        License mitLicense = new License().name("MIT License").url("https://airline.com/licenses/mit/");

        Info info = new Info()
            .title("CheckIn API")
            .version("1.0")
            .contact(contact)
            .description("Esta API expone endpoints para CheckIn.").termsOfService("https://www.airline.com/terms")
            .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}