package nl.asellion.ps.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.api.dto.ProductsDto;
import nl.asellion.ps.configuration.exception.ProductServiceException;

/**
 * Integration tests for ProductService
 *
 * @author Alexander Kirillov
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    @Order(1)
    public void testFindAll() {
        //given
        final int size = 5;

        //when
        final ProductsDto actual = productService.findAll();

        //then
        assertNotNull(actual);
        assertEquals(size, actual.getProductDtoList().size());
    }

    @Test
    @Order(2)
    public void testFindById() {
        //given
        final ProductDto expected = ProductDto.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.now()).build();

        //when
        final ProductDto actual = productService.findById(1L);

        //then
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCurrentPrice(), actual.getCurrentPrice());
        assertEquals(expected.getName(), actual.getName());

    }

    @Test
    @Order(3)
    public void testFindOneNotFound() {
        //given
        final Long id = Long.MAX_VALUE;

        //when //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.findById(id));

    }

    @Test
    @Order(4)
    public void testCreate() {
        //given
        final ProductDto expected = ProductDto.builder().name("Whey Protein Banana")
                .currentPrice(BigDecimal.valueOf(25.99)).lastUpdate(LocalDateTime.now()).build();

        //when
        final ProductDto createdEntity = productService.create(expected);
        final ProductsDto productsDto = productService.findAll();

        //then
        assertNotNull(createdEntity);
        assertEquals(6, productsDto.getProductDtoList().size());

    }

    @Test
    @Order(5)
    public void testCreateWithIdFromDto() {
        //given
        final ProductDto expected = ProductDto.builder().id(1L).name("Whey Protein")
                .currentPrice(BigDecimal.valueOf(25.99)).lastUpdate(LocalDateTime.now()).build();

        //when //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.create(expected));

    }

    @Test
    @Order(6)
    public void testCreateWithIdFromDtoButNotIDb() {
        //given
        final ProductDto expected = ProductDto.builder().id(11L).name("Whey Protein")
                .currentPrice(BigDecimal.valueOf(25.99)).lastUpdate(LocalDateTime.now()).build();

        //when
        final ProductDto createdEntity = productService.create(expected);
        final ProductsDto productsDto = productService.findAll();

        //then
        assertNotNull(createdEntity);
        assertEquals(7, productsDto.getProductDtoList().size());

    }

    @Test
    @Order(7)
    public void testUpdate() {
        //given
        final ProductDto expected = ProductDto.builder().id(2L).name("Apple Macbook Air (2020)")
                .currentPrice(BigDecimal.valueOf(1010.99)).build();

        //when
        final ProductDto actual = productService.update(expected, 2L);

        //then
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCurrentPrice(), actual.getCurrentPrice());
        assertNotNull(actual.getLastUpdate());

    }

    @Test
    @Order(8)
    public void testUpdateNoChangesError() {
        //given
        final ProductDto expected = ProductDto.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.now()).build();

        //when //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.update(expected, 1L));

    }

    @Test
    @Order(9)
    public void testDelete() {
        //given
        final int size = 6;

        //when
        productService.delete(6L);
        final ProductsDto productsDto = productService.findAll();

        //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.findById(6L));
        assertNotNull(productsDto);
        assertEquals(size, productsDto.getProductDtoList().size());

    }

    @Test
    @Order(10)
    public void testDeleteByIdNotFound() {
        //given
        final Long id = 22L;

        //when //then
        Assertions.assertThrows(ProductServiceException.class, () -> productService.delete(id));

    }

}
