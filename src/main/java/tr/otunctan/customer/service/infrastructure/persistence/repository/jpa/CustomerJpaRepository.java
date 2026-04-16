package tr.otunctan.customer.service.infrastructure.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.otunctan.customer.service.infrastructure.persistence.entity.jpa.CustomerJpaEntity;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, UUID> {
    Optional<CustomerJpaEntity> findByEmail(String email);
}
