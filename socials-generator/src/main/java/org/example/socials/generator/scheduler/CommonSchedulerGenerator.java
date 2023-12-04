package org.example.socials.generator.scheduler;

import org.example.socials.generator.model.CommonScheduler;
import org.example.socials.generator.repository.CommonScheduleRepository;
import org.slf4j.Logger;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public abstract class CommonSchedulerGenerator<T extends CommonScheduleRepository<? extends CommonScheduler, String>> {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessagePostProcessor messagePostProcessor;


    protected T commonScheduleRepository;

    protected abstract Logger getLog();

    protected void execCron(String exchange) {
        commonScheduleRepository.getOnesWhoIsReadyToExecute()
                .forEach(x -> {
                    rabbitTemplate.convertAndSend(exchange, "", x.getSchedulerName(), messagePostProcessor);
                    commonScheduleRepository.updateLastTimeFetched(new Date(), x.getSchedulerName());
                    getLog().debug(x.getSchedulerName());
                });
    }

}
