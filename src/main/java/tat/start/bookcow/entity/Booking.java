package tat.start.bookcow.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private Instant fromDatetime;
    private Instant toDatetime;
    private String bookedByName;
    private String bookedByPhone;
    private boolean expired;
}
