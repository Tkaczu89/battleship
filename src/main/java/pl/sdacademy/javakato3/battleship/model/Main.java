package pl.sdacademy.javakato3.battleship.model;

import model.GameBoard;
import model.PlayersBoard;
import model.ShipType;
import pl.sdacademy.javakato3.battleship.model.player.DefaultPlayer;
import pl.sdacademy.javakato3.battleship.model.ui.ConsoleUI;
import pl.sdacademy.javakato3.battleship.model.ui.RandomComputerUI;
import pl.sdacademy.javakato3.battleship.model.validation.AndValidator;
import pl.sdacademy.javakato3.battleship.model.validation.CannotProtrudeOutOfMap;
import pl.sdacademy.javakato3.battleship.model.validation.CannotStartOutOfBounds;
import pl.sdacademy.javakato3.battleship.model.validation.CannotTouchValidator;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConsoleUI humanUI = new ConsoleUI();
        RandomComputerUI cpuUI = new RandomComputerUI();

        DefaultPlayer humanPlayer = new DefaultPlayer(humanUI);
        DefaultPlayer computerPlayer = new DefaultPlayer(cpuUI);

        PlayersBoard humanPlayersBoard = new PlayersBoard();
        PlayersBoard computerPlayersBoard = new PlayersBoard();

        GameBoard gameBoard = new GameBoard(humanPlayersBoard,computerPlayersBoard);
        gameBoard.registerUI(humanUI);
        gameBoard.registerUI(cpuUI);

        ArrayList<ShipType> shipTypes = new ArrayList<>();

        shipTypes.add(ShipType.DESTROYER);
        shipTypes.add(ShipType.BATTLESHIP);

        AndValidator shipPositionValidator = new AndValidator(
                new CannotProtrudeOutOfMap(),
                new CannotStartOutOfBounds(),
                new CannotTouchValidator());

        GameMaster gameMaster = new GameMaster(humanPlayer, computerPlayer, gameBoard, shipPositionValidator, shipTypes);
        gameMaster.setCurrentPlayer(humanPlayer);
        new GameMasterThread(gameMaster).start();


    }
}
