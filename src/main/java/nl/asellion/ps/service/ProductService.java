package nl.asellion.ps.service;

import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.api.dto.ProductsDto;

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

    ProductDto findById(Long id);

    /**
     * Find all Product objects
     *
     * @return A Collection of ProductDto objects
     */

    ProductsDto findAll();

    /**
     * Creates a Product object
     *
     * @param productDto A product object for creation
     * @return A ProductDto object
     */
    ProductDto create(ProductDto productDto);

    /**
     * Updates a Product object
     *
     * @param productDto A Product object for updating
     * @return An ProductDto object
     */
    ProductDto update(ProductDto productDto, Long id);

    /**
     * Deletes a Product object by product id
     *
     * @param id A Product id
     */
    void delete(Long id);
}
