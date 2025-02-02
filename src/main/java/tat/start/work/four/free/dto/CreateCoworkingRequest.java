package tat.start.work.four.free.dto;

import java.util.List;

public record CreateCoworkingRequest(
    String address,
    String name,
    String description,
    Long owner,
    List<CreateSeatRequest> seats,
    Float xCoordinate,
    Float yCoordinate
) {

    public record CreateSeatRequest(
        Integer seatNumber,
        Integer capacity,
        String description
    ) {}
}
