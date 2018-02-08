package pl.sdacademy.javakato3.battleship.model.validation;

import model.PlayersBoard;
import model.Ship;

public interface Validator {
    boolean isValid(Ship ship, PlayersBoard board);

}
