package game.example.server.configs;

import game.example.server.model.Game;
import game.example.server.model.GameDLC;
import game.example.server.model.Key;
import game.example.server.repositories.GameDLCRepository;
import game.example.server.repositories.GameRepository;
import game.example.server.repositories.KeyRepository;
import game.example.server.utils.technical.commands.CommandGetting;
import game.example.server.utils.technical.commands.ReadCommandModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CommandConfig {

    private final GameRepository gameRepository;

    private final GameDLCRepository gameDLCRepository;

    @Bean(name = "commandGameGettingModel")
    public CommandGetting<Game> commandGameGettingModel() {
        var command = new ReadCommandModel<GameRepository, Game>();
        command.setRepository(gameRepository);
        return command;
    }

    @Bean(name = "commandGameDLCGettingModel")
    public CommandGetting<GameDLC> commandGameDLCGettingModel() {
        var command = new ReadCommandModel<GameDLCRepository, GameDLC>();
        command.setRepository(gameDLCRepository);
        return command;
    }
}
