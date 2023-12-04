package org.example.socials.generator.repository;

import org.example.socials.generator.model.ExecutorScheduler;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExecutorSchedulerRepository extends CommonScheduleRepository<ExecutorScheduler, String> {

    @Query(value = "SELECT * " +
            "FROM executor_scheduler " +
            "WHERE CURRENT_TIMESTAMP - last_time_fetched > period * 60 " +
            "AND enable = TRUE", nativeQuery = true)
    List<ExecutorScheduler> getOnesWhoIsReadyToExecute();

    @Query(value = "UPDATE executor_scheduler " +
            "SET last_time_fetched = :lastTimeFetched " +
            "WHERE scheduler_name = :schedulerName", nativeQuery = true)
    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

}
