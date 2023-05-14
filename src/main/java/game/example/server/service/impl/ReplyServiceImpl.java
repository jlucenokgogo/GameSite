package game.example.server.service.impl;

import game.example.server.dto.ReplyDTO;
import game.example.server.exceprion.ModelNoFound;
import game.example.server.mappers.ReplyMapper;
import game.example.server.model.Game;
import game.example.server.model.Reply;
import game.example.server.repositories.GameRepository;
import game.example.server.repositories.ReplyRepository;
import game.example.server.service.abstartion.ReplyService;
import game.example.server.utils.technical.commands.ReadCommandModel;
import game.example.server.utils.technical.iterators.LoadingIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service("ReplyServiceImpl")
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReadCommandModel<GameRepository, Game> gameReadCommandModel;

    private final ReplyRepository replyRepository;

    private final ReplyMapper replyMapper;

    private final LoadingIterator<Reply> replyLoadingIterator;

    @Override
    public ReplyDTO save(ReplyDTO replyDTO) throws ModelNoFound {
        log.info("save new reply");
        var game = gameReadCommandModel.execute(replyDTO.getIdGame());
        var reply = replyMapper.INSTANCE.fromModel_toDTO(replyDTO);
        reply.setGame(game);
        var res = replyRepository.save(reply);
        return replyMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    public ReplyDTO update(ReplyDTO replyDTO) throws ModelNoFound {
        var reply = replyRepository.findById(replyDTO.getIdReply())
                .orElseThrow(() -> new ModelNoFound("not exist"));
        reply.setActiveReply(true);
        var res = replyRepository.save(reply);
        return replyMapper.INSTANCE.toDTO_fromModel(res);
    }

    @Override
    public void delete(Long id) throws ModelNoFound {
        log.info("delete reply");
        if (!replyRepository.existsReplyByIdReply(id)) {
            throw new ModelNoFound("not delete");
        }
        replyRepository.deleteById(id);
    }

    @Override
    public ReplyDTO readOneModel(Long id) throws ModelNoFound {
        var reply = replyRepository.findById(id)
                .orElseThrow(() -> new ModelNoFound("not exist"));
        return replyMapper.INSTANCE.toDTO_fromModel(reply);
    }

    @Async("serviceThreadPool")
    @Override
    public CompletableFuture<ArrayList<ReplyDTO>> getAllReplyByStatus(Boolean active_reply) {
        var listResult = new ArrayList<Reply>();
        while (replyLoadingIterator.hasNext()) {
            var reply = replyLoadingIterator.getNext();
            if (reply.getActiveReply().equals(active_reply)) {
                listResult.add(reply);
            }
        }
        replyLoadingIterator.reset();
        return CompletableFuture.completedFuture(replyMapper
                .toDTO_List_fromModel_List(listResult));
    }

    @Async("serviceThreadPool")
    @Override
    public CompletableFuture<ArrayList<ReplyDTO>> getAllDto() {
        var listResult = new ArrayList<Reply>();
        while (replyLoadingIterator.hasNext()) {
            var reply = replyLoadingIterator.getNext();
            listResult.add(reply);
        }
        replyLoadingIterator.reset();
        return CompletableFuture.completedFuture(replyMapper
                .toDTO_List_fromModel_List(listResult));
    }
}
