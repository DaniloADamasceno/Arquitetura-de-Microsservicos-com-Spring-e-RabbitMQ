package br.com.danilo.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("✅ ✅");
		System.out.println("✅✅ Eureka Server iniciado com sucesso! ✅✅ ");
		System.out.println("Desenvolvido por: Danilo A. Damasceno" + "\n");
		System.out.println("LinkedIn:                       https://www.linkedin.com/in/daniloadamasceno/");
		System.out.println("GitHub: 						https://github.com/DaniloADamasceno");
		System.out.println("Link para acesso:               http://localhost:8081/");
		System.out.println("✅ ✅");
	}
}
