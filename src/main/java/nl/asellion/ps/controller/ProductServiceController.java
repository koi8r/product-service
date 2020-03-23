package nl.asellion.ps.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.service.ProductService;

/**
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

    @RequestMapping(method = RequestMethod.GET, value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> getProducts() {
        final List<Product> products = productService.findAll();
        log.info("products: {}", products);
        return new ResponseEntity<>((products), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getAggregatedPowerOfAttorneys(@PathVariable("id") Long id) {
        final Product product = productService.findById(id);
        log.info("product: {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
