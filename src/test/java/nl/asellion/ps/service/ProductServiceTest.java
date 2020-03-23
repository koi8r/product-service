package nl.asellion.ps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nl.asellion.ps.exception.ProductServiceException;
import nl.asellion.ps.model.Product;

/**
 * @author Alexander Kirillov
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testFindAll() {
        //given
        final int size = 5;

        //when
        final List<Product> actual = productService.findAll();

        //then
        assertEquals(size, actual.size());
    }

    @Test
    public void testFindById() {
        //given
        final Product expected = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.now()).build();

        //when
        final Product actual = productService.findById(1l);

        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCurrentPrice(), actual.getCurrentPrice());
        assertEquals(expected.getName(), actual.getName());

    }

    @Test
    public void testFindOneNotFound() {
        //given
        final Long id = Long.MAX_VALUE;

        //when //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.findById(id));

    }

    @Test
    public void testCreate() {
        //given
        final Product product = Product.builder().name("Whey Protein")
                .currentPrice(BigDecimal.valueOf(25.99)).lastUpdate(LocalDateTime.now()).build();

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
        final Product expected = Product.builder().id(2L).name("Apple Macbook Air (2020)")
                .currentPrice(BigDecimal.valueOf(1010.99)).build();

        //when
        final Product actual = productService.update(expected);

        //then
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCurrentPrice(), actual.getCurrentPrice());
        assertNotNull(actual.getLastUpdate());

    }

    @Test
    public void testDelete() {
        //given
        final int size = 5;

        //when
        productService.delete(6L);
        final List<Product> actualProductList = productService.findAll();

        //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.findById(6L));
        assertNotNull(actualProductList);
        assertEquals(size, actualProductList.size());

    }

}
