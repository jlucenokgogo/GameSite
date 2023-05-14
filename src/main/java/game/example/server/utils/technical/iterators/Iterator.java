package game.example.server.utils.technical.iterators;

public interface Iterator <T>{
    boolean hasNext();

    T getNext();

    void reset();
}
