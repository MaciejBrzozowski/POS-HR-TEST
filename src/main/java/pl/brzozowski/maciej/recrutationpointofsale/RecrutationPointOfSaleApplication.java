package pl.brzozowski.maciej.recrutationpointofsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RecrutationPointOfSaleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecrutationPointOfSaleApplication.class, args);
    }
}
