package nl.asellion.ps.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Alexander Kirillov
 */

@Entity
@Table(name = "product")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Column(name = "name", length = 100, unique = true, nullable = false)
    private String name;

    @NotNull
    @Column(name = "current_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal currentPrice;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

}