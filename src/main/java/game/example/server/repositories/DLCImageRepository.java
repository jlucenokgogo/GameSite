package game.example.server.repositories;

import game.example.server.model.DLCImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("DLCImageRepository")
public interface DLCImageRepository extends JpaRepository<DLCImage, Long> {

    Boolean existsDLCImageByUrl(String url);

    Boolean existsDLCImageByIdImageDLC(Long idImageDLC);
}