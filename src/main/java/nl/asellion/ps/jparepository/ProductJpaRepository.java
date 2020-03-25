package nl.asellion.ps.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product repository interface is a Spring Data JPA data repository for Product entities
 *
 * @author Alexander Kirillov
 */

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}

