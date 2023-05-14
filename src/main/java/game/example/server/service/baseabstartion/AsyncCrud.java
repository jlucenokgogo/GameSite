package game.example.server.service.baseabstartion;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public interface AsyncCrud <T>{

    CompletableFuture<ArrayList<T>> getAllDto();
}
