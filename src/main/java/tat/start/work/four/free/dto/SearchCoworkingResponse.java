package tat.start.work.four.free.dto;

import java.util.UUID;

public record SearchCoworkingResponse(
    UUID id,
    String name,
    String address,
    String owner,
    String description
) {
}
