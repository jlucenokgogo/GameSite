package game.example.server.repositories;

import game.example.server.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("KeyRepository")
public interface KeyRepository extends JpaRepository<Key, Long> {
    Optional<Key> findFirstByActive(Boolean active);
}