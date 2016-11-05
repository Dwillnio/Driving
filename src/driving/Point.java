

package driving;

public class Point implements Movable {
    
    public double x;
    public double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void setPosition(double x, double y) {
        this.x = (int)x;
        this.y = (int)y;
    }

    @Override
    public void move(double dx, double dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void rotate(double radians) {
        double prevX = x;
        double prevY = y;
        
        x = (prevX * Math.cos(radians) - prevY * Math.sin(radians));
        y = (prevX * Math.sin(radians) + prevY * Math.cos(radians));
    }
    
}
