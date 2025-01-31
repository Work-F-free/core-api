package tat.start.bookcow.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.bookcow.entity.Coworking;

public interface CoworkingRepository extends JpaRepository<Coworking, Long> {
    Page<Coworking> findAll(Specification<Coworking> specification, Pageable pageable);
}
