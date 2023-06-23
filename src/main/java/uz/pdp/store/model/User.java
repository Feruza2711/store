package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String password;

    private String firstname;

    private String lastname;
    private String phoneNumber;

    private boolean isEnabled;

    private String email;

    @Column(name = "verification_code", length = 64)
    private String verificationCode;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private List<Address> address;


    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    private Set<Authority> authorities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Card> cards;

    private Date createdAt;

    private Date modifiedAt;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isEnabled=" + isEnabled +
                ", email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", address=" + address +
                ", authorities=" + authorities +
                ", cards=" + cards +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
