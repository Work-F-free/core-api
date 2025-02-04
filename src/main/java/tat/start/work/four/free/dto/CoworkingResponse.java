package tat.start.work.four.free.dto;

import java.util.List;
import java.util.UUID;

public record CoworkingResponse(
        UUID id,
        String name,
        String address,
        String owner,
        List<SeatResponse>seats,
        String description
) {
}
