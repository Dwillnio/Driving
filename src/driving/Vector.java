

package driving;

public class Vector {
    public double x;
    public double y;
    
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double length() {
        return Math.pow(x * x + y * y, 0.5);
    }
    
    public void scale(double factor) {
        x *= factor;
        y *= factor;
    }
    
    public void increase(double amount) {
        double l = length();
        x += x/l * amount;
        y += y/l * amount;
    }
    
    public void addScaleVector(Vector v, double ratio) {
        x += v.x * ratio;
        y += v.y * ratio;
    }
    
    private double currentAngle() {
        return Math.atan(y / x);
    }
    
    public void rotate(double radians) {
        double prevX = x;
        double prevY = y;
        //radians += currentAngle();
        
        x = prevX * Math.cos(radians) - prevY * Math.sin(radians);
        y = prevX * Math.sin(radians) + prevY * Math.cos(radians);
    }
}