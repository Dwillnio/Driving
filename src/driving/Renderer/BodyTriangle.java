package driving.Renderer;

import driving.Point;
import driving.Drawable;
import driving.Movable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class BodyTriangle implements Drawable, Movable {

    public Point a;
    public Point b;
    public Point c;
    
    public final Color color;
    private final Point center;

    public BodyTriangle(Point center, Point a, Point b, Point c, Color color) {
        this.center = center;
        
        this.a = a;
        this.b = b;
        this.c = c;

        this.color = color;
    }

    public BodyTriangle(Point center, Point a, Point b, Point c) {
        this(center, a, b, c, Color.GRAY);
    }
    
    @Override
    public void setPosition(double x, double y) {
        move(x - a.x, y - a.y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);

        
        int[] x = new int[3];
        int[] y = new int[3];

        x[0] = (int)(a.x + center.x);
        y[0] = (int)(a.y + center.y);
        x[1] = (int)(b.x + center.x);
        y[1] = (int)(b.y + center.y);
        x[2] = (int)(c.x + center.x);
        y[2] = (int)(c.y + center.y);
        
        g.fillPolygon(new Polygon(x, y, 3));
    }

    @Override
    public void move(double dx, double dy) {
        a.move(dx, dy);
        b.move(dx, dy);
        c.move(dx, dy);
    }

    @Override
    public void rotate(double radians) {
        Point a1 = new Point(a.x, a.y);
        
        a.rotate(radians);
        b.rotate(radians);
        c.rotate(radians);
    }

}
