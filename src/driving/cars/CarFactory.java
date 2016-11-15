
package driving.cars;

import driving.Drawable;
import driving.Physics.Hitbox;
import driving.Point;
import driving.Renderer.Body;
import driving.Renderer.BodyCircle;
import driving.Renderer.BodyTriangle;
import java.util.ArrayList;
import java.util.List;

public class CarFactory {
    
    public CarFactory() {
       
    }
    
    public Car createStandardCar(double x, double y) {
        return new Car(x, y, initStandardBody(x,y), initStandardHitbox());
    }
    
    private Body initStandardBody(double x, double y) {
        Point c = new Point(x, y);
        List<Drawable> d = new ArrayList<>();
        d.add(new BodyCircle(c, -15, 0, 10));
        d.add(new BodyCircle(c, 15, 0, 10));
        d.add(new BodyTriangle(c, new Point(-15, 0), new Point(15,0), new Point(-15, -15)));
        d.add(new BodyTriangle(c, new Point(15, 0), new Point(15,-15), new Point(-15, -15)));
        Body b = new Body(d, c);
        return b;
    }
    
    private Hitbox initStandardHitbox() {
        return new Hitbox(new Point(-25, -20), new Point(25, 5));
    }
    
}