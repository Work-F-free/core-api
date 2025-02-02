package tat.start.work.four.free.repository.util;

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import tat.start.work.four.free.entity.Coworking;

@UtilityClass
public class CoworkingSpecification {

    @Nonnull
    public static Specification<Coworking> byIdIgnoreNull(@Nullable Integer id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    @Nonnull
    public static Specification<Coworking> byNameIgnoreNull(@Nullable String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    @Nonnull
    public static Specification<Coworking> byNameCaseInsensitiveIgnoreNull(@Nullable String name) {
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
    public static Specification<Coworking> byNameStartsWIth(@Nullable String name) {
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
}
