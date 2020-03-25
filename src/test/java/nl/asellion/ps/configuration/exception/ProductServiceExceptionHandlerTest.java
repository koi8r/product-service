package nl.asellion.ps.configuration.exception;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.asellion.ps.api.controller.ProductServiceController;
import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.service.ProductService;

/**
 * Unit tests for ProductServiceExceptionHandler
 *
 * @author Alexander Kirillov
 */

@ExtendWith(MockitoExtension.class)
public class ProductServiceExceptionHandlerTest {

    @Spy
    @InjectMocks
    private ProductServiceController productServiceController;

    private MockMvc mockMvc;

    @Mock
    public ProductService productService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productServiceController).setControllerAdvice(ProductServiceExceptionHandler.class).build();
    }

    @Test
    public void handleProductServiceException() throws Exception {
        //given
        when(productService.findById(1L)).thenThrow(
                new ProductServiceException(ProductServiceException.ERROR_PRODUCT_NOT_FOUND));
        //when
        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isBadRequest());
        //then
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);

    }

    @Test
    public void handleHttpServerErrorException() throws Exception {
        //given
        when(productService.findById(1L)).thenThrow(
                new HttpServerErrorException(HttpStatus.BAD_REQUEST));
        //when
        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isBadRequest());
        //then
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void handleGlobalException() throws Exception {
        //given
        when(productService.findById(1L)).thenThrow(
                new RuntimeException());
        //when
        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isBadRequest());
        //then
        verify(productService, times(1)).findById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void handleDataIntegrityViolationExceptionException() throws Exception {
        //given
        final ProductDto productToUpdate = ProductDto.builder().id(Long.MAX_VALUE).name("Coronavirus Vaccine")
                .currentPrice(BigDecimal.valueOf(999.99)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();
        when(productService.create(any(ProductDto.class))).thenThrow(
                new DataIntegrityViolationException(""));
        //when
        mockMvc.perform(post("/api/products")
                .content(asJsonString(productToUpdate))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //then
        verify(productService, times(1)).create(any(ProductDto.class));
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
