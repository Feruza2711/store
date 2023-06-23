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
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private Integer parentCategory;

    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
    @ToString.Exclude
    private List<Product> products;

    @ManyToOne
    private Unit unit;

    private Date createdAt;

    private Date modifiedAt;
}


