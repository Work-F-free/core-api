package tat.start.work.four.free.dto;

import jakarta.annotation.Nullable;

public record SearchCoworkingRequest(
        @Nullable String search,
        @Nullable Integer id,
        @Nullable String name
) {
}
