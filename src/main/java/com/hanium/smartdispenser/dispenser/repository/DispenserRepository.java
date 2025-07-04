package com.hanium.smartdispenser.dispenser.repository;

import com.hanium.smartdispenser.dispenser.domain.Dispenser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispenserRepository extends JpaRepository<Dispenser, Long>, DispenserQueryRepository {
}
