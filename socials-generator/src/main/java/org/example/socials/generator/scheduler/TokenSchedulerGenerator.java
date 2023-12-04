package org.example.socials.generator.scheduler;

import org.example.socials.generator.repository.TokenSchedulerRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenSchedulerGenerator extends CommonSchedulerGenerator<TokenSchedulerRepository> {

    private final String exchangeName;

    public TokenSchedulerGenerator(TokenSchedulerRepository tokenRepository,
                                   @Value("${socials.rabbit.exchange.token-refresher.name}") String exchangeName) {
        super.commonScheduleRepository = tokenRepository;
        this.exchangeName = exchangeName;
    }


    @Scheduled(cron = "0 * * ? * *")
    public void tokenRepositoryCron() {
        super.execCron(exchangeName);
    }

    @Override
    protected Logger getLog() {
        return log;
    }

}
