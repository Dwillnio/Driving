package driving.ui;

import driving.Drawable;
import driving.Driving;
import driving.Updateable;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JFrame;

public class Gui implements Runnable, Updateable {

    public static final Dimension STANDARD_SIZE = new Dimension(800, 600);

    private JFrame frame;
    private Driving game;
    private KeyInputHandler keyInputHandler;
    private DrawingBoard drawingBoard;
    private List<Drawable> drawables;

    public Gui(Driving game, List<Drawable> drawables) {
        this.game = game;
        keyInputHandler = new KeyInputHandler(game);
        this.drawables = drawables;
    }

    @Override
    public void run() {
        frame = new JFrame("Driving game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(STANDARD_SIZE);

        createComponents(frame.getContentPane());
        frame.addKeyListener(new KeyInputListener());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        drawingBoard = new DrawingBoard(drawables);
        container.add(drawingBoard);
    }

    public void updateDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
        drawingBoard.updateDrawables(drawables);
    }

    public DrawingBoard getDrawingBoard() {
        return drawingBoard;
    }

    @Override
    public void update() {
        drawingBoard.update();
    }

    @Override
    public void update(double frameTime) {
        update();
    }

    private class KeyInputListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            keyInputHandler.handleTyped(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyInputHandler.handlePress(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyInputHandler.handleRelease(e);
        }

    }
}
