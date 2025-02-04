package tat.start.work.four.free.dto;

import jakarta.annotation.Nullable;
import tat.start.work.four.free.entity.SeatType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record SearchCoworkingRequest(
        @Nullable UUID id,
        @Nullable String name,
        @Nullable List<SeatType> types,
        @Nullable Float priceFrom,
        @Nullable Float priceTo,
        @Nullable Integer capacity,
        @Nullable Instant availableAt
        ) { }
