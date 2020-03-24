package nl.asellion.ps.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import nl.asellion.ps.exception.ProductServiceExceptionHandlerTest;
import nl.asellion.ps.model.Product;
import nl.asellion.ps.service.ProductService;


/**
 * Unit tests for ProductServiceController
 *
 * @author Alexander Kirillov
 */

@ExtendWith(MockitoExtension.class)
public class ProductServiceControllerTest {

    @Spy
    @InjectMocks
    private ProductServiceController productServiceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productServiceController).setControllerAdvice(ProductServiceExceptionHandlerTest.class).build();
    }

    @Mock
    public ProductService productService;

    @Test
    public void getAllProducts() throws Exception {
        //given
        List<Product> productList = new ArrayList<>();
        when(productService.findAll()).thenReturn(productList);

        //when
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void getProductById() throws Exception {
        //given
        Product product = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.now()).build();
        when(productService.findById(product.getId())).thenReturn(product);

        //when
        mockMvc.perform(get("/api/products/{id}", product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Apple EarPods")))
                .andExpect(jsonPath("$.currentPrice", is(16.44)))
                .andExpect(jsonPath("$.lastUpdate", notNullValue()));

        //then
        verify(productService, times(1)).findById(product.getId());
        verifyNoMoreInteractions(productService);
    }

}
