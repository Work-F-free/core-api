package tat.start.bookcow.dto;

import java.time.Instant;

public record CreateBookingRequest(
    String phoneNumber,
    Long coworkingId,
    Integer seatNumber,
    Instant fromDatetime,
    Instant toDatetime
) {
}
