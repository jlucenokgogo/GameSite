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
@Table(name = "games")
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGame", nullable = false)
    private Long idGame;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "game_language_name")
    private String gameLanguageName;

    @Column(name = "youtube")
    private String youtube;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<GameDLC> gameDLCS;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private GameInfo gameInfo;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Key> keys;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<GameImage> gameImages;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true,
            fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private Set<Reply> replies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket", nullable = false)
    private Basket basket;

}
