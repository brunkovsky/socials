package org.example.socials.generator.scheduler;

import org.example.socials.generator.model.ExecutorScheduler;
import org.example.socials.generator.repository.ExecutorSchedulerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExecutorSchedulerGeneratorTest {

    @Mock
    private ExecutorSchedulerRepository executorSchedulerRepository;
    @InjectMocks
    private ExecutorSchedulerGenerator executorSchedulerGenerator = new ExecutorSchedulerGenerator(executorSchedulerRepository);
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private MessagePostProcessor messagePostProcessor;
    @Captor
    private ArgumentCaptor<String> routingKeyCaptor;
    @Captor
    private ArgumentCaptor<String> exchangeKeyCaptor;
    @Captor
    private ArgumentCaptor<Object> messageKeyCaptor;

    @BeforeEach
    void beforeEach() {
        executorSchedulerGenerator.setExchangeName("test-executor");
    }

    @Test
    void ifThereAreCandidatesToExecuteThenExecutorRepositoryInvokesUpdateLastTimeFetchedAndSendsRabbitMessage() {
        given(executorSchedulerRepository.getOnesWhoIsReadyToExecute())
                .willReturn(List.of(TestData.veryOldActiveOneMinuteScheduler));

        executorSchedulerGenerator.executorRepositoryCron();

        then(executorSchedulerRepository)
                .should(times(1))
                .updateLastTimeFetched(any(), any());
        then(rabbitTemplate)
                .should(times(1))
                .convertAndSend(exchangeKeyCaptor.capture(),
                        routingKeyCaptor.capture(),
                        messageKeyCaptor.capture(),
                        any(MessagePostProcessor.class));

        Assertions.assertEquals("test-executor", exchangeKeyCaptor.getValue());
        Assertions.assertEquals("", routingKeyCaptor.getValue());
        Assertions.assertEquals("one_minute", messageKeyCaptor.getValue());
    }

    @Test
    void ifThereAreNoCandidatesToExecuteThenExecutorRepositoryDoesNotInvokeUpdateLastTimeFetchedAndDoesNotSendRabbitMessage() {
        given(executorSchedulerRepository.getOnesWhoIsReadyToExecute())
                .willReturn(List.of());

        executorSchedulerGenerator.executorRepositoryCron();

        then(executorSchedulerRepository).shouldHaveNoMoreInteractions();
        then(rabbitTemplate).shouldHaveNoInteractions();
    }


    interface TestData {
        ExecutorScheduler veryOldActiveOneMinuteScheduler = ExecutorScheduler
                .builder()
                .schedulerName("one_minute")
                .enable(true)
                .lastTimeFetched(new Date(0))
                .period(1)
                .build();

        ExecutorScheduler veryOldInactiveOneMinuteScheduler = ExecutorScheduler
                .builder()
                .schedulerName("one_minute")
                .enable(false)
                .lastTimeFetched(new Date(0))
                .period(1)
                .build();

        ExecutorScheduler lastTimeFetchedIsOneMinuteAgoActiveTenMinutesScheduler = ExecutorScheduler
                .builder()
                .schedulerName("ten_minutes")
                .enable(true)
                .lastTimeFetched(new Date(new Date().getTime() - 60_000)) // calculate current time minus one minute
                .period(10) // ten minutes
                .build();
    }

}