package tat.start.work.four.free.dto;

import java.util.List;

public record CreateCoworkingResponse(
    Long id,
    String address,
    String name,
    String description,
    String owner,
    List<CreateSeatResponse> seats
) {

    public record CreateSeatResponse(
            Long id,
            Integer seatNumber,
            Integer capacity,
            String description
    ) { }
}
