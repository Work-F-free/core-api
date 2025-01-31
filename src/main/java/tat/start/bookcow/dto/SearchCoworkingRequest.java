package tat.start.bookcow.dto;

import jakarta.annotation.Nullable;

public record SearchCoworkingRequest(
        @Nullable String search,
        @Nullable Integer id,
        @Nullable String name
) {
}
