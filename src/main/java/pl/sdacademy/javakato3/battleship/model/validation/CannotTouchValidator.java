package pl.sdacademy.javakato3.battleship.model.validation;

import model.BoardField;
import model.PlayersBoard;
import model.Ship;

import java.util.ArrayList;
import java.util.List;

public class CannotTouchValidator implements Validator {
    @Override
    public boolean isValid(Ship ship, PlayersBoard board) {
        if (board.getShips().isEmpty()) {
            return true;
        }
        int shipX = (int) ship.getPosition().getX();
        int shipEndX = ship.isHorizontal() ? shipX + ship.getType().getSize() : shipX;
        int shipY = (int) ship.getPosition().getY();
        int shipEndY = ship.isHorizontal() ? shipY : shipY + ship.getType().getSize();

        List<BoardField> boardFragment = new ArrayList<>();
        for (int y = shipY - 1; y <= shipEndY + 1; y++) {
            for (int x = shipX - 1; x <= shipEndX + 1; x++) {
                BoardField element = board.getSeaMapElement(x, y);
                boardFragment.add(element);
            }

        }
        return !boardFragment.contains(BoardField.SHIP);
    }
}
