package game.example.server.repositories;

import game.example.server.model.GameDLC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GameDLCRepository")
public interface GameDLCRepository extends JpaRepository<GameDLC, Long> {
    Boolean existsGameDLCByDlsName(String dlsName);
    Boolean existsGameDLCByIdGameDlc(Long idGameDlc);
    List<GameDLC> findGameDLCSByGame_IdGame(Long game_idGame);
}