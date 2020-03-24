package nl.asellion.ps.service;

import java.util.List;

import nl.asellion.ps.model.Product;

/**
 * ProductService interface defines all business behavior for Product data manipulation
 *
 * @author Alexander Kirillov
 */

public interface ProductService {
    /**
     * Find a Product object by product id
     *
     * @param id a Product id
     * @return A Product instance or throws ProductServiceException if the product not found
     */

    Product findById(Long id);

    /**
     * Find all Product objects
     *
     * @return A Collection of Product objects
     */

    List<Product> findAll();

    /**
     * Creates a Product object
     *
     * @param product A product object for creation
     * @return A created Product object
     */
    Product create(Product product);

    /**
     * Updates a Product object
     *
     * @param product A Product object for updating
     * @return An updated Product object
     */
    Product update(Product product);

    /**
     * Deletes a Product object by product id
     *
     * @param id A Product id as
     */
    void delete(Long id);
}
