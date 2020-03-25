package nl.asellion.ps.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ProductsDto Object for successful responses
 *
 * @author Alexander Kirillov
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDto {

    @JsonProperty("productDtoList")
    public List<ProductDto> productDtoList;

}