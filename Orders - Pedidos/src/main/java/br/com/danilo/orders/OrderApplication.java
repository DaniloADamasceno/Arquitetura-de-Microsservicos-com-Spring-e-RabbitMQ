package br.com.danilo.orders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("✅ ✅");
        System.out.println("✅✅ API REST de Pedidos iniciada com sucesso! ✅✅ ");
        System.out.println("Desenvolvido por: Danilo A. Damasceno" + "\n");
        System.out.println("LinkedIn:                       https://www.linkedin.com/in/daniloadamasceno/");
        System.out.println("GitHub:                         https://github.com/DaniloADamasceno");
        System.out.println("Link para acesso:               http://localhost:8080/orders");
        System.out.println("link para acessar o SWAGGER:    http://localhost:8080/swagger-ui.html");

        System.out.println("✅ ✅");
    }
}
