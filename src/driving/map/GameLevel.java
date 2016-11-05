

package driving.map;

import driving.Drawable;
import driving.Point;
import java.awt.Graphics;

public class GameLevel implements Drawable{

    private GameMap map;
    private Point boundary;
    private double gravity;
    
    public GameLevel(GameMap map) {
        this.map = map;
    }
    
    public GameLevel(GameMap map, Point boundary) {
        this.map = map;
        this.boundary = boundary;
    }
    
    public Point getBoundary() {
        return boundary;
    }

    @Override
    public void draw(Graphics g) {
        map.draw(g);
    }
    
    
}
