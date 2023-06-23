package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShoppingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double total;

    @OneToMany(mappedBy = "shoppingSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItem> items;

    private Date createdAt;


    private Date modifiedAt;

    @Override
    public String toString() {
        return "ShoppingSession{" +
                "id=" + id +
                ", user=" + user +
                ", total=" + total +
                ", items=" + items +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
