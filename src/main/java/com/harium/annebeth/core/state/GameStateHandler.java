package com.harium.annebeth.core.state;

public interface GameStateHandler {

    void save(GameState state);

    void load(GameState state);

}
