package org.example.socials.generator.config;

import org.example.socials.generator.model.ExecutorScheduler;
import org.example.socials.generator.model.TokenScheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfiguration implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // Spring Data Rest – Serializing the Entity ID (https://www.baeldung.com/spring-data-rest-serialize-entity-id)
        // this is a way how to expose ids for entities in response
        config.exposeIdsFor(TokenScheduler.class, ExecutorScheduler.class);
    }

}
