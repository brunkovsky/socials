package org.example.socials.storage.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.socials.storage.model.SocialItem;
import org.example.socials.storage.service.SocialsItemService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@EnableRabbit
@AllArgsConstructor
public class StorageListener {

    private ObjectMapper objectMapper;
    private SocialsItemService socialsItemService;

    /*
    new Thread(Runnable r).start() makes it async. in order to not deal with RabbitMQ ack.
    otherwise if it is sync the runtime exception makes rabbit to resend the message again and again.
    maybe it will be better to use other modern ways such as @Async, ExecutorService, etc...
     */
    @RabbitListener(queues = "socials-storage")
    public void catchMessage(String message) {
        new Thread(() -> {
            try {
                List<SocialItem> socialItems = objectMapper.readValue(message, new TypeReference<>() {});
                log.info("catching socials-storage message. socialItems size: {}", socialItems.size());
                socialsItemService.writeSocialItems(socialItems);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
