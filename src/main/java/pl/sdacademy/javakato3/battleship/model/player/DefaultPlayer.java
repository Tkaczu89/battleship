package pl.sdacademy.javakato3.battleship.model.player;

import model.Ship;
import model.ShipType;
import pl.sdacademy.javakato3.battleship.model.ui.UserInterface;

import java.awt.*;

public class DefaultPlayer implements Player {
    private UserInterface ui;

    public DefaultPlayer(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public Ship getNextShip(ShipType type) {
        return ui.askUserForNewShip(type);
    }

    @Override
    public Point getNextShot() {
        return ui.askUserForShot();
    }

    @Override
    public void sendMessege(String msg) {
        ui.notifyUser(msg);

    }
}
