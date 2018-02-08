package pl.sdacademy.javakato3.battleship.model.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.sdacademy.javakato3.battleship.model.GameMaster;
import pl.sdacademy.javakato3.battleship.model.player.Player;
import pl.sdacademy.javakato3.battleship.model.validation.Validator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameMasterTest {

    @Mock
    private Player humanPlayer;
    @Mock
    private PlayersBoard humanPlayerBoard;
    @Mock
    private PlayersBoard computerPlayerBoard;
    @Mock
    private Player computerPlayer;
    @Mock
    private GameBoard gameBoard;
    @Mock
    private Validator shipPositionValidator;
    private List<ShipType> shipTypesToBePlaced;
    private GameMaster gameMaster;

    @Before
    public void init() {
        shipTypesToBePlaced = new ArrayList<>();
        gameMaster = new GameMaster(
                humanPlayer,
                computerPlayer,
                gameBoard,
                shipPositionValidator,
                shipTypesToBePlaced);
        when(gameBoard.getHumanPlayerBoard()).thenReturn(humanPlayerBoard);
        when(gameBoard.getOtherPlayerBoard()).thenReturn(computerPlayerBoard);
    }

    @Test
    public void shouldAskHumanPlayerForShip() {
        //given
        shipTypesToBePlaced.add(ShipType.BATTLESHIP);
        when(shipPositionValidator.isValid(any(), any())).thenReturn(true);


        //when
        gameMaster.placeShips();
        //then
        verify(humanPlayer, times(1)).getNextShip(eq(ShipType.BATTLESHIP));


    }

    @Test
    public void shouldAskComputerPlayerForShip() {
        //given
        shipTypesToBePlaced.add(ShipType.SUBMARINE);
        when(shipPositionValidator.isValid(any(), any())).thenReturn(true);

        //when
        gameMaster.placeShips();
        //then
        verify(computerPlayer, times(1)).getNextShip(eq(ShipType.SUBMARINE));

    }

    @Test
    public void shouldAskPlayerForShipUntilCorrectPositionIsProvided() {
        //given
        shipTypesToBePlaced.add(ShipType.DESTROYER);
        when(shipPositionValidator.isValid(any(), any())).thenReturn(false, false, true, false, false, true);

        //when
        gameMaster.placeShips();
        //then
        verify(humanPlayer, times(3)).getNextShip(eq(ShipType.DESTROYER));
        verify(computerPlayer, times(3)).getNextShip(eq(ShipType.DESTROYER));

    }

    @Test
    public void shouldPlaceCorrectlyPositionedHumanPlayersShipOnTheirsBoard() {
        //given
        shipTypesToBePlaced.add(ShipType.BATTLESHIP);
        Ship playersShip = mock(Ship.class);
        when(humanPlayer.getNextShip(any())).thenReturn(playersShip);
        when(shipPositionValidator.isValid(any(), any())).thenReturn(true);


        //when
        gameMaster.placeShips();

        //then
        verify(humanPlayerBoard, times(1)).addShip(eq(playersShip));


    }

    @Test
    public void shouldPlaceCorrectlyPositionedCpuPlayersShipOnTheirsBoard() {
        //given
        shipTypesToBePlaced.add(ShipType.BATTLESHIP);
        Ship cpuShip = mock(Ship.class);
        when(computerPlayer.getNextShip(any())).thenReturn(cpuShip);
        when(shipPositionValidator.isValid(any(), any())).thenReturn(true);
        when(gameBoard.getOtherPlayerBoard()).thenReturn(computerPlayerBoard);

        //when
        gameMaster.placeShips();

        //then
        verify(computerPlayerBoard, times(1)).addShip(eq(cpuShip));

    }

    @Test
    public void shouldSetSeaMapElementsForHumanPlayersShip() {
        shipTypesToBePlaced.add(ShipType.CRUISER);
        Ship shipToBePlaced = mock(Ship.class);
        when(shipToBePlaced.getPosition()).thenReturn(new Point(2, 1));
        when(shipToBePlaced.isHorizontal()).thenReturn(true);
        when(shipToBePlaced.getType()).thenReturn(ShipType.CRUISER);
        when(humanPlayer.getNextShip(eq(ShipType.CRUISER))).thenReturn(shipToBePlaced);

        when(shipPositionValidator.isValid(any(), any())).thenReturn(true);

        gameMaster.placeShips();

        verify(humanPlayerBoard).setSeaMapElement(eq(2), eq(1), eq(BoardField.SHIP));
        verify(humanPlayerBoard).setSeaMapElement(eq(3), eq(1), eq(BoardField.SHIP));
        verify(humanPlayerBoard).setSeaMapElement(eq(4), eq(1), eq(BoardField.SHIP));

    }

    @Test
    public void shouldSetSeaMapElementsForComputerPlayersShip() {
        fail();

    }

    @Test
    public void shouldAskPlayerForEveryShipTypeDefined() {
        fail();

    }

    @Test
    public void shouldAskFirstPlayerForTheirShot() {
        //given
        gameMaster.setCurrentPlayer(humanPlayer);
        //when
        gameMaster.startTurn();
        //then
        verify(humanPlayer).getNextShot();

    }

    @Test
    public void shouldUpdateSeaMapWithShipHitOnShipHit() {
        //given
        gameMaster.setCurrentPlayer(humanPlayer);
        Point playersShot = new Point(2, 4);
        when(humanPlayer.getNextShot()).thenReturn(playersShot);
        when(computerPlayerBoard.getSeaMapElement(eq(playersShot))).thenReturn(BoardField.SHIP);
        //when
        gameMaster.startTurn();
        verify(computerPlayerBoard).setSeaMapElement(eq(playersShot), eq(BoardField.SHIP_HIT));

    }

    @Test
    public void shouldUpdateShipStatusAfterHit() {
        fail();

    }

    @Test
    public void shouldUpdateSeaMapWithMissWhenMissed() {
        //given
        gameMaster.setCurrentPlayer(computerPlayer);
        Point playersShot = new Point(3, 5);
        when(computerPlayer.getNextShot()).thenReturn(playersShot);
        when(humanPlayerBoard.getSeaMapElement(eq(playersShot))).thenReturn(BoardField.WATER);
        //when
        gameMaster.startTurn();
        verify(humanPlayerBoard).setSeaMapElement(eq(playersShot), eq(BoardField.MISS));

    }

    @Test
    public void shouldNotChangeCurrentUserAfterHit() {
        //given
        gameMaster.setCurrentPlayer(humanPlayer);
        Point playerShot = new Point(3, 6);
        when(humanPlayer.getNextShot()).thenReturn(playerShot);
        when(computerPlayerBoard.getSeaMapElement(eq(playerShot))).thenReturn(BoardField.SHIP);
        //when
        gameMaster.startTurn();
        Player nextTurnPlayer = gameMaster.getCurrentPlayer();
        //then
        assertEquals(humanPlayer, nextTurnPlayer);


    }

    @Test
    public void shouldChangeCurrentUserAfterMissedShoot() {
        //given
        gameMaster.setCurrentPlayer(humanPlayer);
        Point playerShot = new Point(3, 6);
        when(humanPlayer.getNextShot()).thenReturn(playerShot);
        when(computerPlayerBoard.getSeaMapElement(eq(playerShot))).thenReturn(BoardField.WATER);
        //when
        gameMaster.startTurn();
        Player nextTurnPlayer = gameMaster.getCurrentPlayer();
        //then
        assertEquals(computerPlayer, nextTurnPlayer);

    }

    @Test
    public void shouldEmptyOptionalWhenBothPlayersHaveShips() {
        //given
        when(computerPlayerBoard.getSeaMapElement(anyInt(), anyInt())).thenReturn(BoardField.SHIP);
        when(humanPlayerBoard.getSeaMapElement(anyInt(), anyInt())).thenReturn(BoardField.SHIP);
        //when
        Optional<Player> winner = gameMaster.getWinner();
        //then
        assertEquals(Optional.empty(), winner);

    }

    @Test
    public void shouldReturnHumanPlayerWhenComputerHasNoShips() {

        gameMaster = spy(gameMaster);
        //given
        when(humanPlayerBoard.getSeaMapElement(eq(4), eq(1))).thenReturn(BoardField.SHIP);
        when(computerPlayerBoard.getSeaMapElement(anyInt(), anyInt())).thenReturn(BoardField.WATER);
        when(gameMaster.isGameStarted()).thenReturn(true);
        //when
        Optional<Player> winner = gameMaster.getWinner();
        //then
        assertEquals(humanPlayer, winner.get());

    }

    @Test
    public void shouldReturnComputerPlayerWhenHumanHasNoShips() {
        gameMaster = spy(gameMaster);
        //given
        when(computerPlayerBoard.getSeaMapElement(eq(4), eq(1))).thenReturn(BoardField.SHIP);
        when(humanPlayerBoard.getSeaMapElement(anyInt(), anyInt())).thenReturn(BoardField.WATER);
        when(gameMaster.isGameStarted()).thenReturn(true);
        //when
        Optional<Player> winner = gameMaster.getWinner();
        //then
        assertEquals(computerPlayer, winner.get());


    }

    @Test
    public void shouldReturnEmptyOptionalWhenGameHasNotStartedYet() {
        gameMaster = spy(gameMaster);
        when(gameMaster.isGameStarted()).thenReturn(false);
        Optional<Player> winner = gameMaster.getWinner();

        assertEquals(Optional.empty(), winner);
    }

}