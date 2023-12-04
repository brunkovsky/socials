package org.example.socials.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GeneratorApplication {

    //TODO: to find out how to restore connections after cutoff (to rabbit)
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }

}
