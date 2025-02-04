package tat.start.work.four.free.dto;

public record UpdateCoworkingRequest(
        String address,
        String name,
        String description,
        String owner,
        Float longitude,
        Float latitude
) {
}
