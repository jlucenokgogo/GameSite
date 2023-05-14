package game.example.server.repositories;

import game.example.server.model.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("GameInfoRepository")
public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {

    Boolean existsGameInfoByIdGameInfo(Long idGameInfo);
    Boolean existsGameInfoByGame_IdGame(Long game_gameName);
}