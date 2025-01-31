package tat.start.bookcow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tat.start.bookcow.entity.Owner;

public interface UserRepository extends JpaRepository<Owner, Long> {
}
