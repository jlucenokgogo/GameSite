package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Setter
@Getter
@Builder
@Entity
@Table(name = "k_eys")
@AllArgsConstructor
@NoArgsConstructor
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_k_ey",nullable = false)
    private Long idKey;

    @Column(name = "k_ey",nullable = false)
    private String key;

    @Column(name = "active",nullable = false)
    @ColumnDefault(value = "0")
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game")
    private Game game;
}
