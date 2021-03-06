package nl.asellion.ps.jparepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Product entity
 *
 * @author Alexander Kirillov
 */

@Entity
@Table(name = "product")
@Data
public class ProductJpaEntity {

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

    @Column(name = "last_update", nullable = false)
    @CreationTimestamp
    private LocalDateTime lastUpdate;

}