package tat.start.bookcow.dto;

import tat.start.bookcow.entity.Seat;

import java.util.List;

public record CoworkingResponse(
        Long id,
        String name,
        String address,
        Long owner,
        List<Seat>seats,
        String description
) {
}
