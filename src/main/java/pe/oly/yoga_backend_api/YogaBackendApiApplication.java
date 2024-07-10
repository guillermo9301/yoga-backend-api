package pe.oly.yoga_backend_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class YogaBackendApiApplication {

	public static void main(String[] args) {
		// Cargar variables de entorno desde el archivo .env
		Dotenv dotenv = Dotenv.load();

		// Establecer las variables de entorno
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
		SpringApplication.run(YogaBackendApiApplication.class, args);
	}

}
