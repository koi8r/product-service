package nl.asellion.ps.repository;

import java.util.List;
import java.util.Optional;

import nl.asellion.ps.model.Product;

/**
 * Product repository interface
 *
 * @author Alexander Kirillov
 */

public interface ProductRepository {

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product product);

    void deleteById(Long id);

}