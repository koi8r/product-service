package nl.asellion.ps.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.api.dto.ProductsDto;
import nl.asellion.ps.configuration.exception.ProductServiceException;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.repository.ProductRepository;
import nl.asellion.ps.util.ProductMapper;

/**
 * Product service interface implementation
 *
 * @author Alexander Kirillov
 */

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ProductDto findById(Long id) {
        final Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));
        return ProductMapper.INSTANCE.fromModelToDto(product);
    }


    @Override
    public ProductsDto findAll() {
        final List<ProductDto> productDtoList = productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::fromModelToDto).collect(Collectors.toList());

        return ProductsDto.builder().productDtoList(productDtoList).build();
    }

    @Override
    @Transactional
    public ProductDto create(ProductDto productDto) {
        if (productDto.getId() != null) {
            final Optional<Product> foundProductById = productRepository.findById(productDto.getId());
            foundProductById.ifPresent(product -> {
                throw new ProductServiceException(ProductServiceException.ERROR_PRODUCT_ALREADY_EXISTS);
            });

        }

        final Product product = ProductMapper.INSTANCE.fromDtoToModel(productDto);

        return ProductMapper.INSTANCE.fromModelToDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(ProductDto productDto, Long id) {
        final Optional<Product> foundProductById = productRepository.findById(id);
        foundProductById.orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));

        final Product product = foundProductById.get();

        final String currentName = product.getName();
        final BigDecimal currentPrice = product.getCurrentPrice();

        ProductMapper.INSTANCE.mergeDtoIntoModel(productDto, product, LocalDateTime.now());

        if (currentName.equals(product.getName()) && currentPrice.equals(product.getCurrentPrice())) {
            throw new ProductServiceException(ProductServiceException.ERROR_NO_CHANGES_APPLIED);
        }

        final Product savedProduct = productRepository.save(product);
        return ProductMapper.INSTANCE.fromModelToDto(savedProduct);
    }

    @Override
    public void delete(Long id) {
        final Optional<Product> foundProductById = productRepository.findById(id);
        foundProductById.orElseThrow(() -> new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));
        productRepository.deleteById(id);

    }
}
