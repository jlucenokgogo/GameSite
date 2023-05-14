package game.example.server.service.impl;


import game.example.server.dto.KeyDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.KeyMapper;
import game.example.server.model.Game;
import game.example.server.model.Key;
import game.example.server.repositories.GameRepository;
import game.example.server.repositories.KeyRepository;
import game.example.server.service.abstartion.GameService;
import game.example.server.service.abstartion.KeyService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;


@Slf4j
@Service("KeyServiceImpl")
@RequiredArgsConstructor
public class KeyServiceImpl implements KeyService {

    private final KeyMapper keyMapper;

    private final KeyRepository keyRepository;

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;
    @Override
    public void generateKeyForGame(Long id) throws ModelNoFound {
        var game = gameReadCommandModel.execute(id);
        String randomString = RandomStringUtils.random(40, true, true);
        keyRepository.save(Key.builder().key(randomString).active(false).game(game)
                .build());
    }
    @Override
    public KeyDTO getKeyByGameNameForEmail(String gameName, String email) {
        return null;
    }

}
