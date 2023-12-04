package org.example.socials.generator.repository;

import org.example.socials.generator.model.ExecutorScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface CommonScheduleRepository<T, ID> extends JpaRepository<T, ID> {

    List<ExecutorScheduler> getOnesWhoIsReadyToExecute();

    void updateLastTimeFetched(@Param("lastTimeFetched") Date lastTimeFetched, @Param("schedulerName") String schedulerName);

}
