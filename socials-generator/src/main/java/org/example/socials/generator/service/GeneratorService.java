package org.example.socials.generator.service;

import lombok.AllArgsConstructor;
import org.example.socials.generator.repository.ExecutorSchedulerRepository;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class GeneratorService {

    private final RabbitTemplate rabbitTemplate;
    private final ExecutorSchedulerRepository executorSchedulerRepository;
    private final MessagePostProcessor messagePostProcessor;


    public void forceExecute(String exchange) {
        rabbitTemplate.convertAndSend("socials-executor", "", exchange, messagePostProcessor);
        executorSchedulerRepository.updateLastTimeFetched(new Date(), exchange);
    }

}
