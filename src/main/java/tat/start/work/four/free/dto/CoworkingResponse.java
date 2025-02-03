package tat.start.work.four.free.dto;

import tat.start.work.four.free.entity.Seat;

import java.util.List;

public record CoworkingResponse(
        Long id,
        String name,
        String address,
        String owner,
        List<Seat>seats,
        String description
) {
}
