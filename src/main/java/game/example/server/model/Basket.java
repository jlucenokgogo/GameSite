package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "baskets")
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_basket", nullable = false)
    private Long idBasket;

    @OneToMany(mappedBy = "basket", orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<GameDLC> gameDLCS;

    @OneToMany(mappedBy = "basket", orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Game> games;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

}
