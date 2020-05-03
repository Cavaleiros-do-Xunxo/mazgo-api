package net.notfab.mazgo;

import net.notfab.mazgo.repositories.HistoryRepository;
import net.notfab.mazgo.repositories.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackageClasses = {ProductRepository.class, HistoryRepository.class}
)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
