package nl.asellion.ps.exception;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpServerErrorException;

import nl.asellion.ps.controller.ProductServiceController;
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
                .andExpect(status().isNotFound());
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

}
