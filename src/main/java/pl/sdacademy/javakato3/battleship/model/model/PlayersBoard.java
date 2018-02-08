package pl.sdacademy.javakato3.battleship.model.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayersBoard {

    private BoardField[][] seaMap;
    private List<Ship> ships;

    public PlayersBoard() {
        seaMap = new BoardField[10][10];
        for (int i = 0; i < seaMap.length; i++) {
            for (int j = 0; j < seaMap.length; j++) {
                seaMap[i][j] = BoardField.WATER;
            }
        }
        this.ships = new ArrayList<>();
    }

    public BoardField getSeaMapElement(int x, int y) {
        if (isInBounds(x) && isInBounds(y)) {
            return seaMap[x][y];

        }
        return BoardField.NONE;

    }

    private boolean isInBounds(int dimension) {
        return dimension < 10 && dimension >= 0;
    }

    public BoardField getSeaMapElement(Point point) {
        return getSeaMapElement((int) point.getX(), (int) point.getY());

    }

    private boolean isInBounds(Point point) {
        return point.getX() < 10 && point.getX() >= 0;
    }

    public void setSeaMapElement(int x, int y, BoardField newFieldValue) {
        if (isInBounds(x) && isInBounds(y)) {
            seaMap[x][y] = newFieldValue;
        }
    }

    public void setSeaMapElement(Point p, BoardField type) {
        if (isInBounds((int)p.getX()) && isInBounds((int)p.getY()))
        seaMap[(int) p.getX()][(int) p.getY()] = type;

    }

    public void addShip(Ship ship) {
        ships.add(ship);
    }

    public List<Ship> getShips() {
        return ships;
    }
}
