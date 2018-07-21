package pl.brzozowski.maciej.recrutationpointofsale.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.brzozowski.maciej.recrutationpointofsale.repository.InMemoryDB;
import pl.brzozowski.maciej.recrutationpointofsale.repository.UserOrder;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public Logger logger() {
        return getLogger("Application logger");
    }

    @Bean
    public UserOrder userOrder() {
        return new UserOrder();
    }

    @Bean
    public InMemoryDB inMemoryDB() {
        return new InMemoryDB();
    }

}
