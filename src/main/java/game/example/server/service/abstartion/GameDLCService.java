package game.example.server.service.abstartion;

import game.example.server.dto.GameDLCDTO;
import game.example.server.service.baseabstartion.AsyncCrud;
import game.example.server.service.baseabstartion.CrudServiceDTO;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public interface GameDLCService extends CrudServiceDTO<GameDLCDTO>, AsyncCrud<GameDLCDTO> {

    CompletableFuture<ArrayList<GameDLCDTO>> getAllDlcBYGameId(Long idGame);
}
