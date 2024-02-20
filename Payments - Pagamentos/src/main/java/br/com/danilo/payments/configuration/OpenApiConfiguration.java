package br.com.danilo.payments.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {


    @Bean
    public OpenAPI customOpenAPI() { // link para acessar o SWAGGER: http://localhost:8080/swagger-ui.html
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Microsserviço de Pagamentos")
                                .version("1.0.0")
                                .description("Implementação de um Microsserviço de pagamentos")
                );
    }

}
