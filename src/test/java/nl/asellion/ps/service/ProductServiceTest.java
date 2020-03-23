package nl.asellion.ps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import nl.asellion.ps.model.Product;

/**
 * @author Alexander Kirillov
 */

public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testFindAll() {
        //given
        final List<Product> expected = Arrays.asList(Product.builder().build(), Product.builder().build());

        //when
        final List<Product> actual = productService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void testFindById() {
        //given
        final Product expected = Product.builder().build();

        //when
        final Product actual = productService.findById(1l);

        //then
        assertEquals(expected, actual);

    }

    @Test
    public void testFindOneNotFound() {
        //given
        final Long id = Long.MAX_VALUE;

        //when
        final Product actual = productService.findById(id);

        //then
        assertNull(actual);

    }

    @Test
    public void testCreate() {
        //given
        final Product product = Product.builder().build();

        //when
        final Product createdEntity = productService.create(product);
        final List<Product> productList = productService.findAll();

        //then
        assertNotNull(createdEntity);
        assertEquals(6, productList.size());

    }

    @Test
    public void testUpdate() {
        //given
        final Product expected = Product.builder().build();

        //when
        final Product actual = productService.update(expected);

        //then
        assertNotNull(actual);
        assertEquals(expected, actual);

    }

    @Test
    public void testDelete() {
        //given
        final Product expected = Product.builder().build();

        //when
        productService.delete(1L);
        final Product actualProduct = productService.findById(1L);
        final List<Product> actualProductList = productService.findAll();

        //then
        assertNull(actualProduct);
        assertNotNull(actualProductList);
        assertEquals(4, actualProductList.size());

    }

}
