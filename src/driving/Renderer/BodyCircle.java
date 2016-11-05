

package driving.Renderer;

import driving.Point;
import driving.Drawable;
import driving.Movable;
import java.awt.Color;
import java.awt.Graphics;

public class BodyCircle implements Drawable, Movable{

    public double x;
    public double y;
    public double r;
    public Color c;
    private Point center;
    
    public BodyCircle(Point center, int x, int y, int r, Color c) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.c = c;
        this.center = center;
    }
    
    public BodyCircle(Point center, int x, int y, int r) {
        this(center, x, y , r, Color.GRAY);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(c);
        
        g.fillOval((int) (center.x + x - r/2), (int)(center.y + y - r/2), (int)r, (int)r);
    }

    @Override
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }
    
    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void rotate(double radians) {
        double prevX = x;
        double prevY = y;
        
        x = (prevX * Math.cos(radians) - prevY * Math.sin(radians));
        y = (prevX * Math.sin(radians) + prevY * Math.cos(radians));
    }
    
}
