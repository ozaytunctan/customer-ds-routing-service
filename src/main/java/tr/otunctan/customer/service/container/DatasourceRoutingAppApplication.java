package tr.otunctan.customer.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"tr.otunctan.customer.service.infrastructure.persistence.repository.jpa.*"})
@EntityScan(basePackages = {"tr.otunctan.customer.service.infrastructure.persistence.entity.jpa.*"})
@SpringBootApplication(scanBasePackages = "tr.otunctan.customer.service")
public class DatasourceRoutingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatasourceRoutingAppApplication.class, args);
    }

}
