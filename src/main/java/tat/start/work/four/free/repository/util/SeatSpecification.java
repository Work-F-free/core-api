package tat.start.work.four.free.repository.util;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.util.CollectionUtils;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.entity.Seat;
import tat.start.work.four.free.entity.SeatType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class SeatSpecification {

    public static Specification<Seat> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }

    @Nonnull
    public static Specification<Seat> byIdIgnoreNull(@Nullable UUID id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    @Nonnull
    public static Specification<Seat> byNameIgnoreNull(@Nullable String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    @Nonnull
    public static Specification<Seat> byNameCaseInsensitiveIgnoreNull(@Nullable String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || StringUtils.isBlank(name)) {
                return null;
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + EscapeCharacter.DEFAULT.escape(name.toLowerCase()) + "%",
                    EscapeCharacter.DEFAULT.getEscapeCharacter()
            );
        };
    }


    @Nonnull
    public static Specification<Seat> byNameStartsWIth(@Nullable String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }
            return criteriaBuilder.like(
                    root.get("name"),
                    EscapeCharacter.DEFAULT.escape(name) + "%",
                    EscapeCharacter.DEFAULT.getEscapeCharacter()
            );
        };
    }

    public static Specification<Seat> byPriceGreaterOrEqualThanIgnoreNull(Float priceFrom) {
        return (root, query, criteriaBuilder) -> {
            if (priceFrom == null) {
                return null;
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceFrom);
        };
    }

    public static Specification<Seat> byPriceLessOrEqualThanIgnoreNull(Float priceTo) {
        return (root, query, criteriaBuilder) -> {
            if (priceTo == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceTo);
        };
    }

    public static Specification<Seat> byTypeInIgnoreNull(List<SeatType> types) {
        return (root, query, criteriaBuilder) -> {
            if (CollectionUtils.isEmpty(types)) {
                return null;
            }
            return criteriaBuilder.in(root.get("type")).value(types);
        };
    }

    public static Specification<Seat> byCapacityEqualsToIgnoreNull(Integer capacity) {
        return (root, query, criteriaBuilder) -> {
            if (capacity == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("capacity"), capacity);
        };
    }

    public static Specification<Seat> byBookingAvailableAt(Instant time) {
        return (root, query, cb) -> {
            Instant finalTime = time == null? Instant.now() : time;
            Join<Seat, Booking> bookings = root.join("bookings", JoinType.LEFT);
            return cb.or(
                cb.not(cb.between(cb.literal(finalTime), bookings.get("fromDatetime"), bookings.get("toDatetime"))),
                cb.or(
                    cb.isNull(bookings.get("fromDatetime")),
                    cb.isNull(bookings.get("toDatetime"))
                )
            );
        };
    }
}
