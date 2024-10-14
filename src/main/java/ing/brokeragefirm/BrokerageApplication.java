package ing.brokeragefirm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ing.brokeragefirm")
public class BrokerageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrokerageApplication.class, args);
    }
}
