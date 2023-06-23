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
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;

    @ManyToOne
    private ShoppingSession shoppingSession;

    @ManyToOne
    private Product product;

    private Integer amount;

    private Date createdAt;

    private Date modifiedAt;

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
