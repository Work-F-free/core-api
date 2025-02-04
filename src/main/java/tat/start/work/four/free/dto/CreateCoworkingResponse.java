package tat.start.work.four.free.dto;

import tat.start.work.four.free.entity.SeatType;

import java.util.List;
import java.util.UUID;

public record CreateCoworkingResponse(
    UUID id,
    String address,
    String name,
    String description,
    String owner,
    List<CreateSeatResponse> seats,
    Float longitude,
    Float latitude
) {

    public record CreateSeatResponse(
            UUID id,
            SeatType type,
            Integer seatNumber,
            Integer capacity,
            Float price,
            String description
    ) { }
}
