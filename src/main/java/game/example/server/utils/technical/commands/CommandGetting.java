package game.example.server.utils.technical.commands;

import game.example.server.exceprion.ModelNoFound;

public interface CommandGetting<T> {
    T execute(Long id) throws ModelNoFound;

}
