package org.example.socials.generator.repository;

import org.example.socials.generator.model.ExecutorScheduler;
import org.example.socials.generator.model.TokenScheduler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TokenSchedulerRepository extends CommonScheduleRepository<TokenScheduler, String> {

    @Query(value = "SELECT * " +
            "FROM token_scheduler " +
            "WHERE CURRENT_TIMESTAMP - last_time_fetched > period * 60 " +
            "AND enable = TRUE", nativeQuery = true)
    List<ExecutorScheduler> getOnesWhoIsReadyToExecute();

    @Query(value = "UPDATE token_scheduler " +
            "SET last_time_fetched = :lastTimeFetched " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

}
