package game.example.server.utils.technical.commands;

public interface CommandExecute<T, D> {
    void execute(T text, D file);

}
