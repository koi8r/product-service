package nl.asellion.ps.service;

import java.util.List;

import nl.asellion.ps.model.Product;

public interface ProductService {

    Product findById(Long id);

    List<Product> findAll();

    Product create(Product product);

    Product update(Product product);

    void delete(Long id);
}
