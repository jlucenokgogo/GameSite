package game.example.server.utils.technical.iterators;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Component("EntityLoadingIterator")
@Setter
public class EntityLoadingIterator<T, M extends JpaRepository<T, Long>>
        implements LoadingIterator<T> {

    private int position = 0;

    private M repository;

    private List<T> loading = new ArrayList<>();

    private boolean loadingBool = false;

    private boolean hasMoreBool = true;

    @Override
    public boolean hasNext() {
        if (position >= loading.size() && hasMoreBool) {
            fetchNext();
        }
        return position < loading.size();
    }

    @Override
    public T getNext() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return loading.get(position++);
    }

    @Override
    public void reset() {
        position = 0;
        loading.clear();
        loadingBool = false;
        hasMoreBool = true;
    }

    @Override
    public boolean isLoading() {
        return loadingBool;
    }

    @Override
    public boolean hasMore() {
        if (loadingBool) {
            return true;
        } else return hasMoreBool;
    }

    @Override
    @Async("loadingThreadPool")
    public void fetchNext() {
        if (!hasMoreBool) {
            loadingBool = false;
            return;
        }
        int pageSize = 10;
        Pageable pageable = PageRequest.of(position / pageSize, pageSize);
        Page<T> page = repository.findAll(pageable);
        loading.addAll(page.stream().toList());
        hasMoreBool = page.hasNext();
        loadingBool = false;
    }
}
