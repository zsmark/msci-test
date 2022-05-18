package com.msci.test.repository;

import com.msci.test.model.ProcessedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessedMessageRepository extends JpaRepository<ProcessedMessage, Long> {
    List<ProcessedMessage> findAllByOrderByIdAsc();
}
