package game.example.server.service.abstartion;

import game.example.server.dto.KeyDTO;
import game.example.server.exceprion.ModelNoFound;

public interface KeyService {
    void generateKeyForGame(Long id) throws ModelNoFound;
    KeyDTO getKeyByGameNameForEmail(String gameName, String email);
}
