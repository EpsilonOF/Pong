package gui;

import model.RacketController;

public class Player implements RacketController {
    State state = State.IDLE;

    @Override
    public State getState() {
        return state;
    }
}