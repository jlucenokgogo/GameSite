package game.example.server.service.baseabstartion;

import game.example.server.exceprion.ModelExist;
import game.example.server.exceprion.ModelNoFound;

public interface CrudServiceDTO<T> {

    T save(T dto) throws ModelNoFound, ModelExist;

    T update(T dto) throws ModelNoFound;

    void delete(Long id) throws ModelNoFound;

    T readOneModel(Long id) throws ModelNoFound;


}
