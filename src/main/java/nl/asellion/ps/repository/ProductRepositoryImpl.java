package nl.asellion.ps.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import nl.asellion.ps.jparepository.ProductJpaEntity;
import nl.asellion.ps.jparepository.ProductJpaRepository;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.util.ProductMapper;

/**
 * Product repository implementation
 *
 * @author Alexander Kirillov
 */

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    final ProductJpaRepository productJpaRepository;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository) {
        this.productJpaRepository = productJpaRepository;
    }

    /**
     * Finds product from the repository bi id
     *
     * @param id A product id
     * @return A product as <code>Optional</code>
     */

    @Override
    public Optional<Product> findById(Long id) {
        final Optional<ProductJpaEntity> maybeProductJpaEntity = productJpaRepository.findById(id);
        return maybeProductJpaEntity.map(ProductMapper.INSTANCE::fromEntityToModel);
    }

    /**
     * Finds all products
     *
     * @return A Collection of Product objects
     */

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll().stream().map(ProductMapper.INSTANCE::fromEntityToModel).collect(Collectors.toList());
    }

    /**
     * Saves a product
     *
     * @return A Product objects
     */

    @Override
    public Product save(Product product) {
        final ProductJpaEntity productJpaEntity =
                productJpaRepository.save(ProductMapper.INSTANCE.fromModelToEntity(product));
        return ProductMapper.INSTANCE.fromEntityToModel(productJpaEntity);
    }

    /**
     * Deletes a product by id
     */

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }
}
