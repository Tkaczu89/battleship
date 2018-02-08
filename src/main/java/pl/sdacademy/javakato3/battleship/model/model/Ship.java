package pl.sdacademy.javakato3.battleship.model.model;


import java.awt.*;

public class Ship {
    private final ShipType type;
    private final boolean horizontal;
    private ShipState state;
    private Point position;


    public Ship(ShipType type, boolean horizontal, ShipState state, Point position) {
        this.type = type;
        this.horizontal = horizontal;
        this.state = ShipState.OK;
        this.position = position;
    }

    public ShipType getType() {
        return type;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public ShipState getState() {
        return state;
    }

    public Point getPosition() {
        return position;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

}
