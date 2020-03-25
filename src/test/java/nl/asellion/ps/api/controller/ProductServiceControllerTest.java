package nl.asellion.ps.api.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.api.dto.ProductsDto;
import nl.asellion.ps.configuration.exception.ProductServiceExceptionHandler;
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
        mockMvc = MockMvcBuilders.standaloneSetup(productServiceController).setControllerAdvice(ProductServiceExceptionHandler.class).build();
    }

    @Mock
    public ProductService productService;

    @Test
    public void getAllProducts() throws Exception {
        //given
        ProductsDto productList = ProductsDto.builder().build();
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
        ProductDto productDto = ProductDto.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.now()).build();
        when(productService.findById(productDto.getId())).thenReturn(productDto);

        //when
        mockMvc.perform(get("/api/products/{id}", productDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Apple EarPods")))
                .andExpect(jsonPath("$.currentPrice", is(16.44)))
                .andExpect(jsonPath("$.lastUpdate", notNullValue()));

        //then
        verify(productService, times(1)).findById(productDto.getId());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void updateProductNameById() throws Exception {
        //given
        final ProductDto requestProductDto = ProductDto.builder().name("Apple EarPods 2019").build();

        final ProductDto productToUpdate = ProductDto.builder().id(1L).name("Apple EarPods 2019")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        when(productService.update(any(ProductDto.class), anyLong())).thenReturn(productToUpdate);

        //when
        mockMvc.perform(put("/api/products/{id}", 1L)
                .content(asJsonString(requestProductDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(productToUpdate.getName())))
                .andExpect(jsonPath("$.currentPrice", is(16.44)))
                .andExpect(jsonPath("$.lastUpdate", notNullValue()));

        //then
        verify(productService, times(1)).update(any(ProductDto.class), anyLong());
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void createProduct() throws Exception {
        //given
        final ProductDto requestProductDto = ProductDto.builder().name("Coronavirus Vaccine")
                .currentPrice(BigDecimal.valueOf(999.99)).build();

        final ProductDto productToUpdate = ProductDto.builder().id(Long.MAX_VALUE).name("Coronavirus Vaccine")
                .currentPrice(BigDecimal.valueOf(999.99)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        when(productService.create(any(ProductDto.class))).thenReturn(productToUpdate);

        //when
        mockMvc.perform(post("/api/products")
                .content(asJsonString(requestProductDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Long.MAX_VALUE)))
                .andExpect(jsonPath("$.name", is(productToUpdate.getName())))
                .andExpect(jsonPath("$.currentPrice", is(999.99)))
                .andExpect(jsonPath("$.lastUpdate", notNullValue()));

        //then
        verify(productService, times(1)).create(any(ProductDto.class));
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void deleteProductById() throws Exception {
        //given
        final ProductDto requestProductDto = ProductDto.builder().name("Apple EarPods 2019").build();
        doNothing().when(productService).delete(anyLong());

        //when
        mockMvc.perform(delete("/api/products/{id}", 1L)
                .content(asJsonString(requestProductDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //then
        verify(productService, times(1)).delete(anyLong());
        verifyNoMoreInteractions(productService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
