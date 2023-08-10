package system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import system.infrastructure.cachea.CacheConfig;

@Configuration
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "API de Pontos", version = "1.0",
        description = "Essa é uma API de Sistema de Bater Pontos dos funcionario. Contendo três classes Controller, oferecendo GET, POST, PUT e DELETE"))
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
