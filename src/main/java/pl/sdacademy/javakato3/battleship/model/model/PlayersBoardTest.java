package pl.sdacademy.javakato3.battleship.model.model;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayersBoardTest {

    private PlayersBoard board;

    @Before
    public void init() {
        board = new PlayersBoard();
    }

    @Test
    public void shouldReturnWaterAfterInit() {
        //given
        PlayersBoard board = new PlayersBoard();
        //when
        BoardField field = board.getSeaMapElement(4, 7);
        //then
        assertEquals(BoardField.WATER, field);

    }

    @Test
    public void shouldReturnNoneIfXElementIsTooLow() {
        //given
        PlayersBoard board = new PlayersBoard();
        //when
        BoardField field = board.getSeaMapElement(-1, 7);
        //then
        assertEquals(BoardField.NONE, field);

    }

    @Test
    public void shouldReturnNoneIfYElementIsTooLow() {
        //given
        PlayersBoard board = new PlayersBoard();
        //when
        BoardField field = board.getSeaMapElement(0, -7);
        //then
        assertEquals(BoardField.NONE, field);

    }

    @Test
    public void shouldReturnWaterForPointObject() {
        //given
        Point point = mock(Point.class);
        when(point.getX()).thenReturn(5d);
        when(point.getY()).thenReturn(7d);
        //when
        BoardField field = board.getSeaMapElement(point);
        //then
        assertEquals(BoardField.WATER, field);

    }

    @Test
    public void shouldChangeBoardFieldElement() {
        //given
        board.setSeaMapElement(3, 8, BoardField.MISS);
        //when
        BoardField field = board.getSeaMapElement(3, 8);
        //then
        assertEquals(BoardField.MISS, field);
    }

    @Test
    public void shouldNotThrowExceptionWhenElementChangeIsOutOfBound() {
        //given
        board.setSeaMapElement(3, 8, BoardField.MISS);
        //when
        BoardField field = board.getSeaMapElement(10, 2);
        //then
        assertEquals(BoardField.NONE, field);

    }

    @Test
    public void shouldChangeBoardFieldElementForPoint() {
        //given
        Point point = mock(Point.class);
        when(point.getX()).thenReturn(3d);
        when(point.getY()).thenReturn(8d);
        //when
        board.setSeaMapElement(point, BoardField.MISS);
        //then
        BoardField field = board.getSeaMapElement(point);
        assertEquals(BoardField.MISS, field);

    }

    @Test
    public void shouldInitializeShipList(){
        //given
        List<Ship> ships = board.getShips();
        //when
        assertNotNull(ships);
        //then
        assertTrue(ships.isEmpty());
    }

    @Test
    public void shouldAddShipToShipList(){
        //mockowanie sztuczne tworzenie obiektu z jakimiś domyslnymi parametrami- aby nie podawać ich z ręki
        //given
        Ship ship = mock(Ship.class);
        //then
        board.addShip(ship);
        //then
        List<Ship> ships = board.getShips();
        assertNotNull(ships);
        assertEquals(1,ships.size());
    }


}