
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
    
    public Car createStandardCar(Point position) {
        return new Car(position , initStandardBody(position), initStandardHitbox(position));
    }
    
    private Body initStandardBody(Point pos) {
        List<Drawable> d = new ArrayList<>();
        d.add(new BodyCircle(pos, -15, 0, 10));
        d.add(new BodyCircle(pos, 15, 0, 10));
        d.add(new BodyTriangle(pos, new Point(-15, 0), new Point(15,0), new Point(-15, -15)));
        d.add(new BodyTriangle(pos, new Point(15, 0), new Point(15,-15), new Point(-15, -15)));
        Body b = new Body(d, pos);
        return b;
    }
    
    private Hitbox initStandardHitbox(Point pos) {
        return new Hitbox(pos, new Point(-20, -17), new Point(20, 5));
    }
    
}