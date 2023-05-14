package game.example.server.service.abstartion;

import game.example.server.dto.ReplyDTO;
import game.example.server.service.baseabstartion.AsyncCrud;
import game.example.server.service.baseabstartion.CrudServiceDTO;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public interface ReplyService extends CrudServiceDTO<ReplyDTO>, AsyncCrud<ReplyDTO> {

    CompletableFuture<ArrayList<ReplyDTO>> getAllReplyByStatus(Boolean active_reply);
}
