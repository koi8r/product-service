package nl.asellion.ps.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.jparepository.ProductJpaEntity;
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
    public void mapModelToDto() {
        //given
        final Product expected = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        //when
        ProductDto productDto = ProductMapper.INSTANCE.fromModelToDto(expected);

        //then
        assertThat(productDto).isNotNull();
        assertThat(productDto.getId()).isEqualTo(expected.getId());
        assertThat(productDto.getName()).isEqualTo(expected.getName());
        assertThat(productDto.getCurrentPrice()).isEqualTo(expected.getCurrentPrice());
        assertThat(productDto.getLastUpdate().format(PRODUCT_DATE_TIME_PATTERN)).isEqualTo("2020-03-24 13:02:56");

    }

    @Test
    public void mapDtoToModel() {
        //given
        final ProductDto expected = ProductDto.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        //when
        Product product = ProductMapper.INSTANCE.fromDtoToModel(expected);

        //then
        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(expected.getId());
        assertThat(product.getName()).isEqualTo(expected.getName());
        assertThat(product.getCurrentPrice()).isEqualTo(expected.getCurrentPrice());
        assertThat(product.getLastUpdate().format(PRODUCT_DATE_TIME_PATTERN)).isEqualTo("2020-03-24 13:02:56");

    }

    @Test
    public void mapModelToEntity() {
        //given
        final Product expected = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        //when
        ProductJpaEntity productJpaEntity = ProductMapper.INSTANCE.fromModelToEntity(expected);

        //then
        assertThat(productJpaEntity).isNotNull();
        assertThat(productJpaEntity.getId()).isEqualTo(expected.getId());
        assertThat(productJpaEntity.getName()).isEqualTo(expected.getName());
        assertThat(productJpaEntity.getCurrentPrice()).isEqualTo(expected.getCurrentPrice());
        assertThat(productJpaEntity.getLastUpdate().format(PRODUCT_DATE_TIME_PATTERN)).isEqualTo("2020-03-24 13:02:56");

    }

    @Test
    public void mergeDtoIntoModel() {
        //given
        final Product productExpected = Product.builder().id(1L).name("Apple EarPods")
                .currentPrice(BigDecimal.valueOf(16.44)).lastUpdate(LocalDateTime.parse("2020-03-24T13:02:56.208835")).build();

        final ProductDto productDtoExpected = ProductDto.builder().name("Apple EarPods 2000").build();

        //when
        Product mergedProduct = ProductMapper.INSTANCE
                .mergeDtoIntoModel(productDtoExpected, productExpected, LocalDateTime.parse("2020-03-24T13:02:56.208835"));

        //then
        assertThat(mergedProduct).isNotNull();
        assertThat(mergedProduct.getId()).isEqualTo(productExpected.getId());
        assertThat(mergedProduct.getName()).isEqualTo(productDtoExpected.getName());
        assertThat(mergedProduct.getCurrentPrice()).isEqualTo(productExpected.getCurrentPrice());
        assertThat(mergedProduct.getLastUpdate().format(PRODUCT_DATE_TIME_PATTERN)).isEqualTo("2020-03-24 13:02:56");

    }

    @Test
    public void mapModelToDtoNull() {
        //when
        ProductDto productDtoResult = ProductMapper.INSTANCE.fromModelToDto(null);
        Product productResult = ProductMapper.INSTANCE.fromDtoToModel(null);
        ProductJpaEntity productJpaEntityResult = ProductMapper.INSTANCE.fromModelToEntity(null);
        Product productFromEntityResult = ProductMapper.INSTANCE.fromEntityToModel(null);
        Product productMergeResult = ProductMapper.INSTANCE.mergeDtoIntoModel(null, null, null);

        //then
        assertThat(productDtoResult).isNull();
        assertThat(productResult).isNull();
        assertThat(productJpaEntityResult).isNull();
        assertThat(productFromEntityResult).isNull();
        assertThat(productMergeResult).isNull();

    }
}
