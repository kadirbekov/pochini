package kz.manasa.pochini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@ComponentScan
@ImportResource(ArchiverConstants.APPLICATION_CONTEXT_FILE)
@EnableAutoConfiguration
@EnableScheduling
public class Main {

    public static void main(String args[]) {
        SpringApplication.run(Main.class, args);
    }

}
