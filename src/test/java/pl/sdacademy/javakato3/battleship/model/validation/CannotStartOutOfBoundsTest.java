package pl.sdacademy.javakato3.battleship.model.validation;

import org.junit.Before;
import org.junit.Test;
import model.PlayersBoard;
import model.Ship;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CannotStartOutOfBoundsTest {
    private CannotStartOutOfBounds validator;

    @Before
    public void init() {
        validator = new CannotStartOutOfBounds();
    }

    @Test
    public void shouldNotValidateWhenXIsLessThan0() {
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(-1, 1));
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);


        assertFalse(result);

    }

    @Test
    public void shouldNotValidateWhenYIsLessThan0() {
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(1, -1));
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);


        assertFalse(result);

    }

    @Test
    public void shouldNotValidateWhenXIsMoreThan10() {
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(10, 1));
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);


        assertFalse(result);

    }

    @Test
    public void shouldNotValidateWhenYIsMoreThan10() {
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(1, 11));
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);


        assertFalse(result);

    }

    @Test
    public void shouldValidateWhenXis4andYis7(){
        //given
        Ship shipToValidate = mock(Ship.class);
        PlayersBoard boardToValidate = mock(PlayersBoard.class);

        when(shipToValidate.getPosition()).thenReturn(new Point(4, 7));
        //when
        boolean result = validator.isValid(shipToValidate, boardToValidate);


        assertTrue(result);

    }


}