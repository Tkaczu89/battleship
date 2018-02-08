package pl.sdacademy.javakato3.battleship.model.validation;

import org.junit.Before;
import org.junit.Test;
import model.PlayersBoard;
import model.Ship;
import model.ShipType;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CannotProtrudeOutOfMapTest {
    private CannotProtrudeOutOfMap validator;

    @Before
    public void init() {
        validator = new CannotProtrudeOutOfMap();
    }

    @Test
    public void shouldNotValidateWhenBattleshipWithXis8AndYis3(){
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(8, 3));
        when(shipToValidate.getType()).thenReturn(ShipType.BATTLESHIP);
        when(shipToValidate.isHorizontal()).thenReturn(true);
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);

        assertFalse(result);
    }
    @Test
    public void shouldNotValidateWhenBattleshipWithXis5AndYis9(){
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(5, 9));
        when(shipToValidate.getType()).thenReturn(ShipType.CRUISER);
        when(shipToValidate.isHorizontal()).thenReturn(false);
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);

        assertFalse(result);
    }
    @Test
    public void shouldNotValidateWhenBattleshipWithXis4AndYis3(){
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(4, 3));
        when(shipToValidate.getType()).thenReturn(ShipType.DESTROYER);
        when(shipToValidate.isHorizontal()).thenReturn(true);
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);

        assertTrue(result);
    }

}