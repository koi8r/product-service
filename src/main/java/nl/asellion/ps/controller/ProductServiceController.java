package nl.asellion.ps.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.dto.ProductDto;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.service.ProductService;
import nl.asellion.ps.util.ProductMapper;

/**
 * The ProductService Controller class
 *
 * @author Alexander Kirillov
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class ProductServiceController {

    private final ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Product service endpoint to fetch all products
     *
     * @return A ResponseEntity containing a Collection of ProductDto objects
     */

    @RequestMapping(method = RequestMethod.GET, value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDto>> getProducts() {
        final List<Product> products = productService.findAll();
        final List<ProductDto> productDtos = products.stream().map(ProductMapper.INSTANCE::fromProduct)
                .collect(Collectors.toList());
        log.info("product: {}", productDtos);
        return new ResponseEntity<>((productDtos), HttpStatus.OK);
    }

    /**
     * Product service endpoint to fetch a product by id
     *
     * @return A ResponseEntity containing a ProductDto object
     */

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        final Product product = productService.findById(id);
        ProductDto productDto = ProductMapper.INSTANCE.fromProduct(product);
        log.info("product: {}", productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

}
