package pl.sdacademy.javakato3.battleship.model.validation;

import model.PlayersBoard;
import model.Ship;

public class CannotStartOutOfBounds implements Validator {

    @Override
    public boolean isValid(Ship ship, PlayersBoard board) {
        return !(ship.getPosition().getX() < 0) && !(ship.getPosition().getY() < 0) && !(ship.getPosition().getX() >= 10) && !(ship.getPosition().getY() >= 10);
    }
}
