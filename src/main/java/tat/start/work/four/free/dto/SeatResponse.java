package tat.start.work.four.free.dto;

import tat.start.work.four.free.entity.SeatType;

import java.util.List;
import java.util.UUID;

public record SeatResponse(
    UUID id,
    SeatType type,
    Integer seatNumber,
    Integer capacity,
    Float price,
    String description,
    List<BookingResponse> bookings
) {
}
