package nl.asellion.ps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.asellion.ps.model.Product;

/**
 * @author Alexander Kirillov
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}