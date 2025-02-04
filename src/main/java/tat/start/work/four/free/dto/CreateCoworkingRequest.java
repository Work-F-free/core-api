package tat.start.work.four.free.dto;

import tat.start.work.four.free.entity.SeatType;

import java.util.List;

public record CreateCoworkingRequest(
    String address,
    String name,
    String description,
    String owner,
    List<CreateSeatRequest> seats,
    Float longitude,
    Float latitude
) {

    public record CreateSeatRequest(
        SeatType type,
        Integer seatNumber,
        Integer capacity,
        Float price,
        String description
    ) {}
}
