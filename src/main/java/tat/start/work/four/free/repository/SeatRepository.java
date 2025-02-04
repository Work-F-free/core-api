package tat.start.work.four.free.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.work.four.free.entity.Seat;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findAll(Specification<Seat> specification);
}
