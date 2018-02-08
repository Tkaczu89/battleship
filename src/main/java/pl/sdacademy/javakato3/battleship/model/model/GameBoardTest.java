package pl.sdacademy.javakato3.battleship.model.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class GameBoardTest {
    private GameBoard gameBoard;

    @Before
    public void init(){
        gameBoard = new GameBoard(mock(PlayersBoard.class),mock(PlayersBoard.class));
    }

    @Test
    public void shouldProvideHumanPlayerBoard(){
        //given

        //when
        PlayersBoard humanPlayerBoard = gameBoard.getHumanPlayerBoard();
        //then
        assertNotNull(humanPlayerBoard);
    }
    @Test
    public void shouldProvideOtherPlayerBoard(){
        //given

        //when
        PlayersBoard otherPlayerBoard = gameBoard.getOtherPlayerBoard();
        //then
        assertNotNull(otherPlayerBoard);
    }

    @Test
    public void shouldProvideDifferentPlayersBoards(){
        //given

        //when
        PlayersBoard humanPlayerBoard = gameBoard.getHumanPlayerBoard();
        PlayersBoard otherPlayerBoard = gameBoard.getOtherPlayerBoard();
        //then
        assertNotEquals(humanPlayerBoard,otherPlayerBoard);

    }



}