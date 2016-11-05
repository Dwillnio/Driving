

package driving.Physics;

import driving.Point;
import driving.Vector;

public class Hitbox {

    private final Point upperLeft;
    private final Point lowerRight;
    
    private final Vector orientation;
    
    public Hitbox(Point upperLeft, Point lowerRight) {
        this.lowerRight = lowerRight;
        this.upperLeft = upperLeft;
        
        orientation = new Vector(1, 0);
    }
    
    public void rotate(double radians) {
        upperLeft.rotate(radians);
        lowerRight.rotate(radians);
        orientation.rotate(radians);
    }
    
    public boolean hittingPoint(Point a) { //Point's position must be relative to hitboxes Position i.e. it must be relative to the hitboxes center 0,0
        Point ul = new Point(upperLeft.x, upperLeft.y);
        ul.rotate(-orientation.currentAngle());
        Point lr = new Point(lowerRight.x, lowerRight.y);
        lr.rotate(-orientation.currentAngle());
        return a.x >= ul.x && a.x <= lr.x && a.y <= ul.y && a.y >= lr.y;
    }
    
}
