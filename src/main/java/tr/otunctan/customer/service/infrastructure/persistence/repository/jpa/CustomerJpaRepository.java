package tr.otunctan.customer.service.infrastructure.persistence.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.otunctan.customer.service.application.query.model.CustomerView;
import tr.otunctan.customer.service.infrastructure.persistence.entity.jpa.CustomerJpaEntity;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, UUID> {

    @Query("""
                  select
                  new tr.otunctan.customer.service.application.query.model.CustomerView(c.id,c.firstName,c.lastName,c.email)
                  from CustomerJpaEntity c
                  where c.email=:email

            """)
    CustomerView findByEmail(@Param("email") String email);
}
