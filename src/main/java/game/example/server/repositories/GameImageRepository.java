package game.example.server.repositories;

import game.example.server.model.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("GameImageRepository")
public interface GameImageRepository extends JpaRepository<GameImage, Long> {

    Boolean existsGameImageByUrl(String url);

    Boolean existsGameImageByIdImage(Long idImage);

}