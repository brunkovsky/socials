package org.example.socials.generator.scheduler;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.generator.repository.ExecutorSchedulerRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExecutorSchedulerGenerator extends CommonSchedulerGenerator<ExecutorSchedulerRepository> {

    @Setter
    @Value("${socials.rabbit.exchange.executor.name}")
    private String exchangeName;

    public ExecutorSchedulerGenerator(ExecutorSchedulerRepository executorRepository) {
        super.commonScheduleRepository = executorRepository;
    }


    @Scheduled(cron = "0 * * ? * *")
    public void executorRepositoryCron() {
        super.execCron(exchangeName);
    }

    @Override
    protected Logger getLog() {
        return log;
    }

}
