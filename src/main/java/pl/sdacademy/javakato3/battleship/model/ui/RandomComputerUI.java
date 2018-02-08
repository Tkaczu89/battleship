package pl.sdacademy.javakato3.battleship.model.ui;

import model.*;

import java.awt.*;
import java.util.Random;

public class RandomComputerUI implements UserInterface {

    private PlayersBoard opponentsBoard;

    @Override
    public void printMaps(GameBoard gameBoard) {
        opponentsBoard = gameBoard.getHumanPlayerBoard();

    }

    @Override
    public void notifyUser(String string) {

    }

    @Override
    public Point askUserForShot() {
        Random r = new Random();
        Point shot;
        do {
            int xShot = r.nextInt(10);
            int yShot = r.nextInt(10);
            shot = new Point(xShot, yShot);
        }
        while (!opponentsBoard.getSeaMapElement(shot).equals(BoardField.WATER) || !opponentsBoard.getSeaMapElement(shot).equals(BoardField.SHIP));
        return shot;

    }

    @Override
    public Ship askUserForNewShip(ShipType shipType) {
        Random r = new Random();
        return new Ship(shipType, isHorizontal(r), ShipState.OK, new Point(r.nextInt(10), r.nextInt(10)));
    }


    private boolean isHorizontal(Random r) {
        return r.nextBoolean();
    }

}
