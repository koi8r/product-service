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
 * @author Alexander Kirillov
 */

@Value
@Builder
public class ProductDto {

    private static final String PRODUCT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @NotNull
    @JsonProperty("id")
    private final Long id;

    @NotNull
    @JsonProperty("name")
    private final String name;

    @NotNull
    @JsonProperty("currentPrice")
    private final BigDecimal currentPrice;

    @NotNull
    @JsonFormat(pattern = PRODUCT_DATE_TIME_PATTERN)
    @JsonProperty("lastUpdate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private final LocalDateTime lastUpdate;

}