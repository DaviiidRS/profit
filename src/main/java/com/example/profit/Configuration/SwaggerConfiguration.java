package com.example.profit.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API PROFIT")
                        .version("1.0")
                        .description("Documentación de la API de PROFIT")
                        .contact(new Contact()
                                .name("Juan David Ramírez")
                                .email("jdramirezsantana@ucundinamarca.edu.co"))
                        .contact(new Contact()
                                .name("Daniel Felipe Caidedo")
                                .email("dfelipecaicedo@ucundinamarca.edu.co"))
                );
    }
}
