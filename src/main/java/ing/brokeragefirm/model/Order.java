package ing.brokeragefirm.model;

import ing.brokeragefirm.enums.OrderSide;
import ing.brokeragefirm.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "stock_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String assetName;
    private String orderSide; // BUY or SELL
    private int size;
    private BigDecimal price;
    private String status; // PENDING, MATCHED, CANCELED
    private LocalDateTime createDate;
}
