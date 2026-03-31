package com.messaging.producer.domain.repository;

import com.messaging.producer.domain.entities.Origin;

import java.util.Optional;

public interface OriginsRepository {
    Optional<Origin> findByPhoneNumber(String phoneNumber);
}
