package com.hanium.smartdispenser.history.repository;

import com.hanium.smartdispenser.history.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long>, HistoryQueryRepository {
}
