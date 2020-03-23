package nl.asellion.ps.service;

import java.util.List;

import org.springframework.stereotype.Service;

import nl.asellion.ps.model.Product;

/**
 * @author Alexander Kirillov
 */

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
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
