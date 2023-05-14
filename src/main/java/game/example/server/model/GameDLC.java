package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "game_dlc")
@AllArgsConstructor
@NoArgsConstructor
public class GameDLC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_dlc", nullable = false)
    private Long idGameDlc;

    @Column(name = "dla_name", nullable = false)
    private String dlsName;

    @Column(name = "price_dlc", nullable = false)
    private Double priceDlc;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "youtube")
    private String youtube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket", nullable = false)
    private Basket basket;


    @OneToMany(mappedBy = "gameDLC", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<DLCImage> gameDlcImages;
}
