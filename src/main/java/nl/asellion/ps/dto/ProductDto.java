package nl.asellion.ps.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Value;

/**
 * Product Data Transfer Object for successful responses
 *
 * @author Alexander Kirillov
 */

@Value
@Builder
public class ProductDto {

    private static final String PRODUCT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @NotNull
    @JsonProperty("id")
    Long id;

    @NotNull
    @JsonProperty("name")
    String name;

    @NotNull
    @JsonProperty("currentPrice")
    BigDecimal currentPrice;

    @NotNull
    @JsonFormat(pattern = PRODUCT_DATE_TIME_PATTERN)
    @JsonProperty("lastUpdate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime lastUpdate;

}