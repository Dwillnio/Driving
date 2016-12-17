package driving;

import driving.Physics.CollisionDetector;
import driving.Physics.Hitbox;
import driving.cars.Car;
import driving.cars.CarFactory;
import driving.map.GameLevel;
import driving.map.GameMap;
import driving.map.GroundNode;
import driving.ui.Gui;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class Driving {

    public static void main(String[] args) throws InterruptedException {
        Driving game = new Driving();
        while (game.getGui() == null) {
            Thread.sleep(10);
        }
        SwingUtilities.invokeLater(game.getGui());
        while (game.getGui().getDrawingBoard() == null) {
            Thread.sleep(10);
        }
        game.start();
    }

    /*------------------------------------------------*/
    private final static long FRAMERATE_TARGET = 120;
    private final static double FRAMETIME = 1000.0 / FRAMERATE_TARGET;
    public final static double INTENDED_FRAMETIME = 10.0;

    private final Gui gui;
    private final Car car;
    private final GameLevel level;
    private final List<Drawable> drawables;
    private final List<Updateable> updateables;
    private final List<Hitbox> collideables;
    private final CollisionDetector collisionSystem;

    private boolean reportMode;
    private double lastFrametime;

    public Driving() {
        reportMode = false;
        lastFrametime = 10;

        GameMap map = initMap();
        level = new GameLevel(map);
        
        CarFactory carFactory = new CarFactory();
        
        //TEMP
        Car cTemp = carFactory.createStandardCar(new Point(500, 500));

        car = carFactory.createStandardCar(new Point(100, 100));
        drawables = new ArrayList();
        drawables.add(level);
        drawables.add(car);
        drawables.add(cTemp);

        gui = new Gui(this, drawables);

        updateables = new ArrayList<>();
        updateables.add(car);
        updateables.add(gui);
        updateables.add(cTemp);
        
        collideables = new ArrayList();
        collideables.add(car.getHitbox());
        collideables.add(cTemp.getHitbox());
        collisionSystem = new CollisionDetector(this);
        
        updateables.add(collisionSystem);
    }

    private GameMap initMap() {
        GameMap m = new GameMap();
        m.add(new GroundNode(0, 0));
        m.add(new GroundNode(100, 50));
        m.add(new GroundNode(250, 100));
        m.add(new GroundNode(400, 100));
        return m;
    }

    public void start() {
        gameLoop();
    }

    private void gameLoop() {
        long sleepNanos;
        long measureNanos;
        long frameCounter = 0;
        while (true) {
            sleepNanos = System.nanoTime();
            measureNanos = System.nanoTime();

            for (Updateable up : updateables) {
                up.update((int) lastFrametime);
            }

            sleepNanos = System.nanoTime() - sleepNanos;

            try {
                frameCounter++;
                double waitTime = FRAMETIME * 1000000 - sleepNanos;
                if (waitTime > 0) {
                    Thread.sleep((long) waitTime / 1000000, (int)((long)waitTime%1000000));
                }
            } catch (Exception ex) {
            }

            if (reportMode) {
                if (measureNanos > 0) {
                    reportGameStatus(frameCounter);
                }
            }

            measureNanos = System.nanoTime() - measureNanos;
            lastFrametime = measureNanos / 1000000.0;
        }
    }

    public void reportGameStatus(long frameCounter) {
        System.out.println("Framerate: " + (int) (1000 / lastFrametime) + " fps" + "  ||  Frametime: " + lastFrametime + " ms"
                + "  ||  Frame# " + frameCounter);
        System.out.println("Car| x:" + (int) car.getX() + " y: " + (int) car.getY()
                + " speed: " + (int) car.getVelocity().length() + " direction: " + car.getOrientation().currentAngle()*180/Math.PI) ;
    }

    public int lastFrametime() {
        return (int) lastFrametime;
    }
    
    public GameLevel getLevel() {
        return level;
    }

    public Gui getGui() {
        return gui;
    }

    public Car getCar() {
        return car;
    }

    public boolean reportMode() {
        return reportMode;
    }
    
    public List<Hitbox> getCollideables() {
        return collideables;
    }

    public void setReportMode(boolean value) {
        reportMode = value;
    }

    public void setGasFlag(boolean onGas) {
        car.setGas(onGas);
    }
}
