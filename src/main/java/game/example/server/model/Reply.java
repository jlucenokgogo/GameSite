package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Date;

@Setter
@Getter
@Builder
@Entity
@Table(name = "replies")
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reply", nullable = false)
    private Long idReply;

    @Column(name = "registration", nullable = false)
    private Date registration;

    @Column(name = "de_sc", nullable = false)
    private String desc;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "active_reply", nullable = false)
    private Boolean activeReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_game")
    private Game game;

}
