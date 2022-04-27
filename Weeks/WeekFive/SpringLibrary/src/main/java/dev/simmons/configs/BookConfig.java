package dev.simmons.configs;

import dev.simmons.data.BookDAO;
import dev.simmons.data.BookDAOPostgres;
import dev.simmons.services.BookService;
import dev.simmons.services.Library;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BookConfig {
    @Bean(name = "Service")
    public BookService service() {
        return new Library(dao());
    }
    @Bean(name = "BookDao")
    public BookDAO dao() {
        return new BookDAOPostgres();
    }
}
