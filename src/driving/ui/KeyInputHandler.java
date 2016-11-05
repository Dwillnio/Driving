package driving.ui;

import driving.Driving;
import java.awt.event.KeyEvent;

public class KeyInputHandler {

    //KEY CONFIG//
    private final static int GAS = KeyEvent.VK_W;
    private final static int BRAKE = KeyEvent.VK_S;
    private final static int STEER_UP = KeyEvent.VK_Q;
    private final static int STEER_DOWN = KeyEvent.VK_E;
    private final static int ROTATE_LEFT = KeyEvent.VK_A;
    private final static int ROTATE_RIGHT = KeyEvent.VK_D;

    private final Driving game;

    public KeyInputHandler(Driving game) {
        this.game = game;
    }

    public void handlePress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case GAS:
                game.getCar().setGas(true);
                break;
            case STEER_UP:
                game.getCar().setVelocityTurningUp(true);
                break;
            case STEER_DOWN:
                game.getCar().setVelocityTurningDown(true);
                break;
            case ROTATE_LEFT:
                game.getCar().setRotatingUp(true);
                break;
            case ROTATE_RIGHT:
                game.getCar().setRotatingDown(true);
                break;
        }
    }

    public void handleRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case GAS:
                    game.getCar().setGas(false);
                break;
            case STEER_UP:
                    game.getCar().setVelocityTurningUp(false);
                break;
            case STEER_DOWN:
                    game.getCar().setVelocityTurningDown(false);
                break;
            case ROTATE_LEFT:
                    game.getCar().setRotatingUp(false);
                break;
            case ROTATE_RIGHT:
                    game.getCar().setRotatingDown(false);
                break;
            case KeyEvent.VK_F1:
                if (game.reportMode() == false) {
                    game.setReportMode(true);
                } else {
                    game.setReportMode(false);
                }
                break;
        }
    }

    public void handleTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_F1:
                if (game.reportMode() == false) {
                    game.setReportMode(true);
                } else {
                    game.setReportMode(false);
                }
                break;
        }
    }
}
