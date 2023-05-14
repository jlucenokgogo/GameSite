package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "refresh_token")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_refresh_token", nullable = false)
    private Long idRefreshToken;

}
