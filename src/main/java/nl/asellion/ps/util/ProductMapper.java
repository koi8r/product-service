package nl.asellion.ps.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import nl.asellion.ps.dto.ProductDto;
import nl.asellion.ps.model.Product;

/**
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
    ProductDto map(Product account);
}
