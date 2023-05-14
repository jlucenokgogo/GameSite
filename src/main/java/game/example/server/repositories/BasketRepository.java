package game.example.server.repositories;

import game.example.server.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("BasketRepository")
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findBasketByUser_Username(String user_username);

}