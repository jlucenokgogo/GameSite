package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "DLC_Image")
@AllArgsConstructor
@NoArgsConstructor
public class DLCImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image_dlc", nullable = false)
    private Long idImageDLC;

    @Column(name = "image_url", nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game_dcl")
    private GameDLC gameDLC;
}
