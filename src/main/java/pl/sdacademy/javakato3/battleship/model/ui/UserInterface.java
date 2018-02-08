package pl.sdacademy.javakato3.battleship.model.ui;

import model.GameBoard;
import model.Ship;
import model.ShipType;

import java.awt.*;

public interface UserInterface {
    void printMaps(GameBoard gameBoard);

    void notifyUser(String string);

    Point askUserForShot();

    Ship askUserForNewShip(ShipType shipType);


}
