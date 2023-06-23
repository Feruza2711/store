package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)

    private Integer id;

    private String name;

    private String description;

    private Double discountPercent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
    @ToString.Exclude
    private List<Product> products;

    private Date createdAt;

    private Date modifiedAt;
}
