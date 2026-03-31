package com.messaging.producer.infrastructure.persistency;

import com.messaging.producer.domain.entities.Origin;
import com.messaging.producer.domain.repository.OriginsRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaOriginRepositoryAdapter implements OriginsRepository {
    private final SpringDataOriginRepository repository;

    public JpaOriginRepositoryAdapter(SpringDataOriginRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Origin> findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }
}
