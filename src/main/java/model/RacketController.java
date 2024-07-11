package model;

public interface RacketController {
    enum State { GOING_UP, IDLE, GOING_DOWN, GOING_LEFT, GOING_RIGHT }
    State getState();
}
