package tat.start.work.four.free.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.work.four.free.entity.Owner;

public interface UserRepository extends JpaRepository<Owner, Long> {
}
