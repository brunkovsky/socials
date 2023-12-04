package org.example.socials.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialsStorageApplication {

    //TODO: to find out how to restore connections after cutoff (to db)
    public static void main(String[] args) {
        SpringApplication.run(SocialsStorageApplication.class, args);
    }

}
