package nl.asellion.ps.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Product model
 *
 * @author Alexander Kirillov
 */


@Builder(toBuilder = true)
@Data
public class Product {

    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal currentPrice;

    @NotNull
    private LocalDateTime lastUpdate;

}