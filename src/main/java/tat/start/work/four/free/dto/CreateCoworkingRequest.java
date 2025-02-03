package tat.start.work.four.free.dto;

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
        Integer seatNumber,
        Integer capacity,
        String description
    ) {}
}
