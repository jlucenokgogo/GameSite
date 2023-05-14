package game.example.server.configs;

import game.example.server.model.*;
import game.example.server.repositories.*;
import game.example.server.utils.technical.iterators.EntityLoadingIterator;
import game.example.server.utils.technical.iterators.LoadingIterator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LoadingConfigs {

    private final ReplyRepository replyRepository;

    private final GameRepository gameRepository;

    private final GameDLCRepository gameDLCRepository;

    @Bean
    public LoadingIterator<Reply> loadingReplyIterator() {
        var iterator = new EntityLoadingIterator<Reply, ReplyRepository>();
        iterator.setRepository(replyRepository);
        return iterator;
    }

    @Bean
    public LoadingIterator<Game> loadingGameIterator() {
        var iterator = new EntityLoadingIterator<Game, GameRepository>();
        iterator.setRepository(gameRepository);
        return iterator;
    }

    @Bean
    public LoadingIterator<GameDLC> loadingGameDLCIterator() {
        var iterator = new EntityLoadingIterator<GameDLC, GameDLCRepository>();
        iterator.setRepository(gameDLCRepository);
        return iterator;
    }

}
