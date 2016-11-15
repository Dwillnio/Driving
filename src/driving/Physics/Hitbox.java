

package driving.Physics;

import driving.Drawable;
import driving.Point;
import driving.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class Hitbox {

    private final Point upperLeft;
    private final Point lowerRight;
    
    private final Vector orientation;
    
    public Hitbox(Point upperLeft, Point lowerRight) {
        this.lowerRight = lowerRight;
        this.upperLeft = upperLeft;
        
        orientation = new Vector(lowerRight.x - upperLeft.x, 0);
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
    
    private Vector normalOrientation() {
        return new Vector(lowerRight.x - (upperLeft.x + orientation.x), lowerRight.y - (upperLeft.y +orientation.y));
    }
    
    private boolean hittingVector(Point source, Vector vector) {
        return VectorCollision.vectorHittingVector(upperLeft, orientation, source, vector) 
                || VectorCollision.vectorHittingVector(upperLeft, normalOrientation(), source, vector) 
                || VectorCollision.vectorHittingVector(lowerRight, normalOrientation().inverseVec(), source, vector) 
                || VectorCollision.vectorHittingVector(upperLeft, orientation.inverseVec(), source, vector);
    }
    
    public boolean hittingBox(Hitbox h) {
        return hittingVector(h.upperLeft, h.orientation)
                || hittingVector(h.upperLeft, h.normalOrientation())
                || hittingVector(h.lowerRight, h.orientation.inverseVec())
                || hittingVector(h.lowerRight, h.normalOrientation().inverseVec())
                || hittingPoint(h.upperLeft)
                || hittingPoint(h.lowerRight)
                || hittingPoint(new Point(h.upperLeft.x + h.orientation.x , h.upperLeft.y + h.orientation.y))
                || hittingPoint(new Point(h.lowerRight.x + h.orientation.inverseVec().x , h.lowerRight.y + h.orientation.inverseVec().y));
    }

    public void draw(Graphics g, double x, double y) {
        g.setColor(Color.red);
        
        g.drawLine((int)(upperLeft.x + x), (int)(upperLeft.y + y), (int)(upperLeft.x + orientation.x + x), (int)(upperLeft.y + orientation.y + y));
        g.drawLine((int)(upperLeft.x + x), (int)(upperLeft.y + y), 
                (int)(upperLeft.x + normalOrientation().x + x), (int)(upperLeft.y + normalOrientation().y + y));
        g.drawLine((int)(lowerRight.x + x), (int)(lowerRight.y + y), 
                (int)(lowerRight.x + orientation.inverseVec().x + x), (int)(lowerRight.y + orientation.inverseVec().y + y));
        g.drawLine((int)(lowerRight.x + x), (int)(lowerRight.y + y), 
                (int)(lowerRight.x + normalOrientation().inverseVec().x + x), (int)(lowerRight.y + normalOrientation().inverseVec().y + y));
    }
    
    
    
}
