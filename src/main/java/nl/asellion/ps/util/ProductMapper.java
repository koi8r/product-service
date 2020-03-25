package nl.asellion.ps.util;

import java.time.LocalDateTime;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import nl.asellion.ps.api.dto.ProductDto;
import nl.asellion.ps.jparepository.ProductJpaEntity;
import nl.asellion.ps.model.Product;

/**
 * Mapper between Product, ProductDto and ProductJpaEntity
 *
 * @author Alexander Kirillov
 */

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "currentPrice", target = "currentPrice"),
            @Mapping(source = "lastUpdate", target = "lastUpdate"),
    })
    ProductDto fromModelToDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(source = "productDto.id", target = "product.id"),
            @Mapping(source = "productDto.name", target = "product.name"),
            @Mapping(source = "productDto.currentPrice", target = "product.currentPrice"),
            @Mapping(source = "dateTime", target = "product.lastUpdate"),
    })
    Product mergeDtoIntoModel(ProductDto productDto, @MappingTarget Product product, LocalDateTime dateTime);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "currentPrice", target = "currentPrice"),
            @Mapping(source = "lastUpdate", target = "lastUpdate"),
    })
    Product fromDtoToModel(ProductDto productDto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "currentPrice", target = "currentPrice"),
            @Mapping(source = "lastUpdate", target = "lastUpdate"),
    })
    Product fromEntityToModel(ProductJpaEntity productJpaEntity);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "currentPrice", target = "currentPrice"),
            @Mapping(source = "lastUpdate", target = "lastUpdate"),
    })
    ProductJpaEntity fromModelToEntity(Product product);

}
