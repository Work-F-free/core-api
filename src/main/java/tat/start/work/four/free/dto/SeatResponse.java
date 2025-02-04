package tat.start.work.four.free.dto;

import java.util.List;

public record SeatResponse(
    Long id,
    Integer number,
    Integer capacity,
    String description,
    List<BookingResponse> bookings
) {
}
