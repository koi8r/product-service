package nl.asellion.ps.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.api.dto.ProductsDto;
import nl.asellion.ps.service.ProductService;

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

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsDto> getProducts() {
        final ProductsDto productsDto = productService.findAll();
        return new ResponseEntity<>((productsDto), HttpStatus.OK);
    }

    /**
     * Product service endpoint to fetch a product by id
     *
     * @return A ResponseEntity containing a ProductDto object
     */

    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        final ProductDto productDto = productService.findById(id);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    /**
     * Product service endpoint to update a product by id
     *
     * @return A ResponseEntity containing a ProductDto object
     */

    @PutMapping(value = "/products/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProductById(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        final ProductDto updatedProductDto = productService.update(productDto, id);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    /**
     * Product service endpoint to create a product
     *
     * @return A ResponseEntity containing a ProductDto object
     */

    @PostMapping(value = "/products",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        final ProductDto updatedProductDto = productService.create(productDto);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    /**
     * Product service endpoint to delete a product by id
     *
     * @return A ResponseEntity containing a ProductDto object
     */
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<ProductDto> deleteProductById(@PathVariable("id") Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
