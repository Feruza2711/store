package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private OrderDetails orderDetails;

    private Integer amount;

    private Date createdAt;

    @Override
    public String toString() {
        return "OrderItems{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                '}';
    }
}
