package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "game_info")
@AllArgsConstructor
@NoArgsConstructor
public class GameInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_info", nullable = false)
    private Long idGameInfo;

    @Column(name = "de_sc",nullable = false)
    private String desc;

    @Column(name = "newM",nullable = false)
    private String newM;

    @OneToOne
    @JoinColumn(name = "idGame")
    private Game game;
}
