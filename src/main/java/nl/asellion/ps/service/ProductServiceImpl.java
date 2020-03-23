package nl.asellion.ps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import nl.asellion.ps.model.Product;
import nl.asellion.ps.repository.ProductRepository;

/**
 * @author Alexander Kirillov
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        final Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> findAll() {
        final List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
