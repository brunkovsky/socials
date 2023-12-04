package org.example.socials.generator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class CommonScheduler { // TODO: to make the class abstract ?

    @Id
    @Column(length = 128)
    private String schedulerName;

    private boolean enable;

    private int period;

    private Date lastTimeFetched;

}
