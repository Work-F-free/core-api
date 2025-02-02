package tat.start.work.four.free.dto;

import java.time.Instant;

public record CreateBookingRequest(
    String phoneNumber,
    Long coworkingId,
    Integer seatNumber,
    Instant fromDatetime,
    Instant toDatetime
) {
}
