package nl.asellion.ps.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Product Data Transfer Object for successful responses
 *
 * @author Alexander Kirillov
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {

    private static final String PRODUCT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @ApiModelProperty(value = "{Product Id}")
    @JsonProperty("id")
    Long id;

    @ApiModelProperty(value = "{Product name}")
    @JsonProperty("name")
    String name;

    @JsonProperty("currentPrice")
    @ApiModelProperty(value = "{Product price}")
    BigDecimal currentPrice;

    @ApiModelProperty(value = "{Product date and time}")
    @JsonFormat(pattern = PRODUCT_DATE_TIME_PATTERN)
    @JsonProperty("lastUpdate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime lastUpdate;

}