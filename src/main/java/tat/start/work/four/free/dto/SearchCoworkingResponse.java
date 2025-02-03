package tat.start.work.four.free.dto;

public record SearchCoworkingResponse(
    Long id,
    String name,
    String address,
    String owner,
    String description
) {
}
