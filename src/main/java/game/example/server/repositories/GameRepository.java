package game.example.server.repositories;

import game.example.server.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("GameRepository")
public interface GameRepository extends JpaRepository<Game, Long> {
    Boolean existsGameByGameName(String gameName);
    Boolean existsGameByIdGame(Long idGame);

}