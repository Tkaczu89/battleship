package pl.sdacademy.javakato3.battleship.model.player;

import model.Ship;
import model.ShipType;

import java.awt.*;

public interface Player {
    Ship getNextShip(ShipType type);

    Point getNextShot();

    void sendMessege(String msg);

}
