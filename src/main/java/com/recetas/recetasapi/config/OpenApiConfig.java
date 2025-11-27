package com.recetas.recetasapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para documentación de la API.
 * Aplica SRP - solo se encarga de configurar la documentación.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI recetasOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Servidor Local");

        Server dockerServer = new Server();
        dockerServer.setUrl("http://localhost:8080");
        dockerServer.setDescription("Servidor Docker");

        Contact contact = new Contact();
        contact.setEmail("recetas@example.com");
        contact.setName("Equipo Recetas API");
        contact.setUrl("https://github.com/tu-usuario/recetas-api");

        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("API de Recetas de Cocina")
                .version("1.0.0")
                .contact(contact)
                .description("API REST completa para gestionar recetas de cocina. " +
                        "Permite crear, leer, actualizar y eliminar recetas, además de buscar por diversos criterios.")
                .termsOfService("https://example.com/terms")
                .license(mitLicense);

        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer, dockerServer));
    }
}