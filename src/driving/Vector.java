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
    
    public void add(Vector v) {
        x += v.x;
        y += v.y;
    }
    
    public void subtract(Vector v) {
        x -= v.x;
        y -= v.y;
    }

    public void scale(double factor) {
        x *= factor;
        y *= factor;
    }

    public void increase(double amount) {
        double l = length();
        x += x / l * amount;
        y += y / l * amount;
    }

    public void addScaleVector(Vector v, double ratio) {
        x += v.x * ratio;
        y += v.y * ratio;
    }
    
    public Vector inverseVec() {
        return new Vector(-x, -y);
    }

    public double currentAngle() { 
        return Math.atan2(y, -x) + Math.PI;
    }

    public void rotate(double radians) {
        double prevX = x;
        double prevY = y;

        x = prevX * Math.cos(radians) - prevY * Math.sin(radians);
        y = prevX * Math.sin(radians) + prevY * Math.cos(radians);
    }
    
    public double dotProduct(Vector v) {
        return x * v.x + y * v.y;
    }
    
    public Vector unitVector() {
        double length = Math.sqrt(x*x + y*y);
        return new Vector(x / length, y / length);
    }
    
    public Vector clone() {
        return new Vector(x,y);
    }
}
