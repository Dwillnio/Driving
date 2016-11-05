

package driving.Renderer;

import driving.Point;
import driving.Drawable;
import driving.Movable;
import driving.Vector;
import java.awt.Graphics;
import java.util.List;

public class Body implements Drawable, Movable{
    
    private List<Drawable> drawables; //All body drawables are also movables
    private final Point center;
    private Vector orientation;
    
    public Body(List<Drawable> drawables, Point center, Vector orientation) {
        this.center = center;
        this.drawables = drawables;
        this.orientation = orientation;
    }
    
    public Body(List<Drawable> drawables, Point center) {
        this(drawables, center, new Vector(1,0));
    }
    
    @Override 
    public void draw(Graphics g) {
        for(Drawable d: drawables) {
            d.draw(g);
        }
    }

    @Override
    public void move(double dx, double dy) {
        center.x += dx;
        center.y += dy;
    }
    
    @Override
    public void setPosition(double x, double y) {
        center.x = (int)x;
        center.y = (int)y;
    }

    @Override
    public void rotate(double radians) {
        for(Drawable m: drawables) {
            ((Movable)m).rotate(radians);
        }
        orientation.rotate(radians);
    }
    
}
