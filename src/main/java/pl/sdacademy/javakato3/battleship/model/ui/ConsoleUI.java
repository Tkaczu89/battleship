package pl.sdacademy.javakato3.battleship.model.ui;

import model.*;

import java.awt.*;

public class ConsoleUI implements UserInterface {
    private JavaConsoleDelegate consoleDelegate = new JavaConsoleDelegate();

    @Override
    public void printMaps(GameBoard gameBoard) {
        consoleDelegate.printToConsole("     YOU         OPPONENT");
        consoleDelegate.printToConsole("");
        consoleDelegate.printToConsole("  ABCDEFGHIJ    ABCDEFGHIJ");
        for (int y = 0; y < 10; y++) {
            String humanLine = getLine(y, gameBoard.getHumanPlayerBoard());
            String otherLine = getLine(y, gameBoard.getOtherPlayerBoard()).replace("O", " ");
            consoleDelegate.printToConsole(humanLine + "  " + otherLine);
        }
    }

    private String getLine(int y, PlayersBoard board) {
        StringBuilder builder = new StringBuilder();
        if (y+1 < 10) {
            builder.append(" ");
        }
        builder.append(y+1);
        for (int x = 0; x < 10; x++) {
            Point address = new Point(x, y);
            BoardField mapElement = board.getSeaMapElement(address);
            builder.append(decodeElement(mapElement));
        }
        return builder.toString();
    }

    private String decodeElement(BoardField mapElement) {
        switch (mapElement) {
            case SHIP:
                return "O";
            case SHIP_HIT:
                return "X";
            case MISS:
                return "*";
            default:
                return " ";
        }
    }

    @Override
    public void notifyUser(String message) {
        consoleDelegate.printToConsole(message);

    }

    @Override
    public Point askUserForShot() {
        notifyUser("Where do you want to shot?");
        String userInput = consoleDelegate.readFromConsole();
        return getPointFromUser(userInput);
    }

    private Point getPointFromUser(String userInput) {
        if (userInput.length() < 2 || userInput.length() > 3) {
            return new Point(-1, -1);
        }
        try {
            return new Point(userInput.toUpperCase().charAt(0) - 'A', Integer.parseInt(userInput.substring(1)) - 1);
        } catch (NumberFormatException e) {
            return new Point(-1, -1);
        }
    }

    @Override
    public Ship askUserForNewShip(ShipType shipType) {
        notifyUser("Where do you want to place " + shipType + " ?");
        String userInput = consoleDelegate.readFromConsole().toUpperCase();
        notifyUser(shipType + " should be placed horizontal? (Y/N)");
        String userInputHorizontal = consoleDelegate.readFromConsole().toUpperCase();
        return new Ship(shipType,isHorizontal(userInputHorizontal),ShipState.OK,getPointFromUser(userInput));

    }

    private boolean isHorizontal(String userInputHorizontal) {
        switch (userInputHorizontal) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                return true;
        }
    }
}
