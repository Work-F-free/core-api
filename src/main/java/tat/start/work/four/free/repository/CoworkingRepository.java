package tat.start.work.four.free.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.work.four.free.entity.Coworking;

import java.util.Collection;
import java.util.UUID;

public interface CoworkingRepository extends JpaRepository<Coworking, UUID> {
    Page<Coworking> findAll(Specification<Coworking> specification, Pageable pageable);

    Page<Coworking> findAllByIdIn(Collection<UUID> ids, Pageable pageable);
}
