package tat.start.work.four.free.dto;

import java.time.Instant;

public record BookingResponse(
    Instant from,
    Instant to
) {
}
