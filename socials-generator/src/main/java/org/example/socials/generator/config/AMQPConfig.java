package org.example.socials.generator.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {   // creates Social exchanges dynamically

    @Bean
    FanoutExchange executorExchange(@Value("${socials.rabbit.exchange.executor.name}") String exchangeName) {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    FanoutExchange tokenRefresherExchange(@Value("${socials.rabbit.exchange.token-refresher.name}") String exchangeName) {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    MessagePostProcessor messagePostProcessor(){
        return message -> {
            message.getMessageProperties().setExpiration("30000");
            return message;
        };
    }

}
