package nl.asellion.ps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.exception.ProductServiceException;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.repository.ProductRepository;

/**
 * Product service interface implementation
 *
 * @author Alexander Kirillov
 */

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product create(Product product) {
        return productRepository.save(product);
    }

    /**
     * {@inheritDoc}
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Product update(Product product) {
        final Optional<Product> foundProductById = productRepository.findById(product.getId());
        foundProductById.orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));

        final Product productToUpdate = foundProductById.get().toBuilder().name(product.getName())
                .currentPrice(product.getCurrentPrice()).build();

        return productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        final Optional<Product> foundProductById = productRepository.findById(id);
        foundProductById.orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);

    }
}
