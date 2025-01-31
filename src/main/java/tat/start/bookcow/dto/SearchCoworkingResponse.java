package tat.start.bookcow.dto;

public record SearchCoworkingResponse(
    Long id,
    String name,
    String address,
    Long owner,
    String description
) {
}
