package nl.asellion.ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.asellion.ps.model.Product;

/**
 * Product repository interface is a Spring Data JPA data repository for Product entities
 *
 * @author Alexander Kirillov
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}