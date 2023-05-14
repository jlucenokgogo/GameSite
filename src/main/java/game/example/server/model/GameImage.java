package game.example.server.model;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@Entity
@Table(name = "game_images")
@AllArgsConstructor
@NoArgsConstructor
public class GameImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image",nullable = false)
    private Long idImage;

    @Column(name = "image_url")
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGame")
    private Game game;

}
