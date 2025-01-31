package tat.start.bookcow.dto;

import java.time.Instant;

public record BookingResponse(
    Instant from,
    Instant to
) {
}
