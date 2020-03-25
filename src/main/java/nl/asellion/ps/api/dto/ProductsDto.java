package nl.asellion.ps.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "{List of all products}")
    public List<ProductDto> productDtoList;

}