package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String fullName;

    private String phoneNumber;

    @ManyToOne
    private City city;

    private String region;

    private String street;

    private Integer homeNumber;

    private String landMark;

    private Date createdAt;

    private Date modifiedAt;

}
