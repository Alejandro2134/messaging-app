package com.messaging.producer.infrastructure.persistency;

import com.messaging.producer.domain.entities.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataOriginRepository extends JpaRepository<Origin, Long> {
    Optional<Origin> findByPhoneNumber(String phoneNumber);
}
