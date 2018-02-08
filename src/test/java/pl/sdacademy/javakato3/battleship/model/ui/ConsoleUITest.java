package pl.sdacademy.javakato3.battleship.model.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import model.Ship;
import model.ShipType;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsoleUITest {

    @Mock
    private JavaConsoleDelegate consoleDelegate;
    @InjectMocks
    private ConsoleUI ui;

    @Test
    public void shouldDisplayTxtMessageSendToUser() {
        //given
        String messageToTheUser = "Bardzo się starales lecz z gry wyleciales";
        //when
        ui.notifyUser(messageToTheUser);
        //then
        verify(consoleDelegate).printToConsole(eq(messageToTheUser));
    }

    @Test
    public void shouldReadShotThatIsUpperCaseAndSingleNumber() {
        when(consoleDelegate.readFromConsole()).thenReturn("F2");
        Point userShot = ui.askUserForShot();
        assertEquals(5, (int) userShot.getX());
        assertEquals(1, (int) userShot.getY());
    }

    @Test
    public void shouldReadShotThatIsLowerCaseAndSingleNumber() {
        when(consoleDelegate.readFromConsole()).thenReturn("f2");
        Point userShot = ui.askUserForShot();
        assertEquals(5, (int) userShot.getX());
        assertEquals(1, (int) userShot.getY());
    }

    @Test
    public void shouldReadShotThatIsLowerCaseAndDoubleNumber() {
        when(consoleDelegate.readFromConsole()).thenReturn("d10");
        Point userShot = ui.askUserForShot();
        assertEquals(3, (int) userShot.getX());
        assertEquals(9, (int) userShot.getY());
    }

    @Test
    public void shouldReadHorizontalBattleShipFromUser(){
        //given
        when(consoleDelegate.readFromConsole()).thenReturn("c4","Y");
        //when
        Ship ship = ui.askUserForNewShip(ShipType.BATTLESHIP);
        //then
        assertEquals(ShipType.BATTLESHIP,ship.getType());
        assertEquals(Boolean.TRUE,ship.isHorizontal());
        assertEquals(2, (int) ship.getPosition().getX());
        assertEquals(3, (int) ship.getPosition().getY());



    }

    @Test
    public void shouldReadVerticalDestroyerFromUser(){
        //given
        when(consoleDelegate.readFromConsole()).thenReturn("c4","N");
        //when
        Ship ship = ui.askUserForNewShip(ShipType.BATTLESHIP);
        //then
        assertEquals(ShipType.BATTLESHIP,ship.getType());
        assertEquals(Boolean.FALSE,ship.isHorizontal());
        assertEquals(2, (int) ship.getPosition().getX());
        assertEquals(3, (int) ship.getPosition().getY());

    }


}