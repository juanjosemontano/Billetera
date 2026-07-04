package com.wallet.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI walletOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Wallet API")
                        .description("API REST que simula una billetera virtual: " +
                                "registro de clientes, recarga de saldo, y flujo de " +
                                "pago con confirmación por token enviado a email.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Yehiron")
                                .email("yehiron@ejemplo.com"))
                        .license(new License()
                                .name("Uso interno - Prueba técnica")));
    }
}