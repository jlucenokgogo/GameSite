package game.example.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "e_role", nullable = false)
    private ERole eRole;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private Basket basket;

}
