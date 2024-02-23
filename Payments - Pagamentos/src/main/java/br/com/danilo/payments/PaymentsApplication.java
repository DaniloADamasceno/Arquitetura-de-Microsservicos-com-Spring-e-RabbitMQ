package br.com.danilo.payments;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static java.lang.System.*;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PaymentsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        out.println("✅ ✅");
        out.println("✅✅ API REST de Pagamentos iniciada com sucesso! ✅✅ ");
        out.println("Desenvolvido por: Danilo A. Damasceno" + "\n");
        out.println("LinkedIn:                       https://www.linkedin.com/in/daniloadamasceno/");
        out.println("GitHub:                         https://github.com/DaniloADamasceno");
        out.println("Link para acesso:               http://localhost:8080/payments");
        out.println("link para acessar o SWAGGER:    http://localhost:8080/swagger-ui.html");

        out.println("✅ ✅");
    }
}
