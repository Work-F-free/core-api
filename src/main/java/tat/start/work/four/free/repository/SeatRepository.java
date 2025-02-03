package tat.start.work.four.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.work.four.free.entity.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
