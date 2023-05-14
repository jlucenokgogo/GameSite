package game.example.server.utils.technical.commands;

import game.example.server.exceprion.ModelNoFound;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("ReadCommandModel")
@Setter
public class ReadCommandModel<M extends JpaRepository<T, Long>, T> implements CommandGetting<T> {

    private M repository;

    @Override
    public T execute(Long id) throws ModelNoFound {
        return repository.findById(id)
                .orElseThrow(() -> new ModelNoFound("model not found"));
    }
}
