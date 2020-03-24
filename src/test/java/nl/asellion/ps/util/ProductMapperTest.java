package nl.asellion.ps.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.asellion.ps.dto.ProductDto;
import nl.asellion.ps.model.Product;

/**
 * Unit tests for ProductMapper
 *
 * @author Alexander Kirillov
 */

@ExtendWith(MockitoExtension.class)
public class ProductMapperTest {

    private static final DateTimeFormatter PRODUCT_DATE_TIME_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void mapAccountToAccountDto() {
        //given
        final Product expected = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        //when
        ProductDto productDto = ProductMapper.INSTANCE.fromProduct(expected);

        //then
        assertThat(productDto).isNotNull();
        assertThat(productDto.getId()).isEqualTo(expected.getId());
        assertThat(productDto.getName()).isEqualTo(expected.getName());
        assertThat(productDto.getCurrentPrice()).isEqualTo(expected.getCurrentPrice());
        assertThat(productDto.getLastUpdate().format(PRODUCT_DATE_TIME_PATTERN)).isEqualTo("2020-03-24 13:02:56");

    }
}
