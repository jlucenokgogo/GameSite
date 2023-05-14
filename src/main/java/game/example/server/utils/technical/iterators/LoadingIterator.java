package game.example.server.utils.technical.iterators;

public interface LoadingIterator<T> extends Iterator<T> {

    boolean isLoading();
    boolean hasMore();
    void fetchNext();
}
