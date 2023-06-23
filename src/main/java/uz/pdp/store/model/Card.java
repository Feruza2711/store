package uz.pdp.store.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String ownerName;

    private String cardNumber;

    private String validity;

    private Double amount;

    private Integer cvc;

    @ManyToOne
    private User user;

    @ManyToOne
    private CardType cardType;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", validity='" + validity + '\'' +
                ", cvc=" + cvc +
                ", cardType=" + cardType +
                '}';
    }
}
