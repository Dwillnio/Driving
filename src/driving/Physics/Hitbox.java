package driving.Physics;

import driving.Drawable;
import driving.Point;
import driving.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class Hitbox implements Drawable {

    private final Point upperLeft;
    private final Point lowerRight;
    private final Point position;

    private final Vector orientation;

    public Hitbox(Point position, Point upperLeft, Point lowerRight) {
        this.lowerRight = lowerRight;
        this.upperLeft = upperLeft;
        this.position = position;

        orientation = new Vector(lowerRight.x - upperLeft.x, 0);
    }

    public void rotate(double radians) {
        upperLeft.rotate(radians);
        lowerRight.rotate(radians);
        orientation.rotate(radians);
    }

    public boolean hittingPoint(Point a) {
        Point ul = new Point(upperLeft.x, upperLeft.y);
        ul.rotate(orientation.currentAngle());
        Point lr = new Point(lowerRight.x, lowerRight.y);
        lr.rotate(orientation.currentAngle());
        Point p = a.clone();
        p.rotate(orientation.currentAngle());
        /*
        System.out.println("UL: " + (int)ul.x + "|" + (int)ul.y);
        System.out.println("LR: " + (int)lr.x + "|" + (int)lr.y);
        System.out.println("P: " + (int)p.x + "|" + (int)p.y);
        System.out.println("Hit: " +(p.x >= ul.x && p.x <= lr.x && p.y <= ul.y && p.y >= lr.y));
        System.out.println("" + (p.x >= ul.x) + " " + (p.x <= lr.x) + " " + (p.y <= ul.y)  + " " + (p.y >= lr.y));
         */
        return (p.x >= ul.x) && (p.x <= lr.x) && (p.y >= ul.y) && (p.y <= lr.y); //y comparision that way bcs of inverse y-axis
    }

    private Vector normalOrientation() {
        return new Vector(lowerRight.x - (upperLeft.x + orientation.x), lowerRight.y - (upperLeft.y + orientation.y));
    }

    private boolean hittingVector(Point source, Vector vector) {
        return VectorCollision.vectorHittingVector(upperLeft, orientation, source, vector)
                || VectorCollision.vectorHittingVector(upperLeft, normalOrientation(), source, vector)
                || VectorCollision.vectorHittingVector(lowerRight, normalOrientation().inverseVec(), source, vector)
                || VectorCollision.vectorHittingVector(upperLeft, orientation.inverseVec(), source, vector);
    }

    public boolean hittingBox(Hitbox h) {
        Point otherUpperLeft = new Point(h.upperLeft.x + (h.position.x - position.x),
                h.upperLeft.y + (h.position.y - position.y));
        Point otherLowerRight = new Point(h.lowerRight.x + (h.position.x - position.x),
                h.lowerRight.y + (h.position.y - position.y));
        
        return hittingPoint(otherUpperLeft)
                || hittingPoint(otherLowerRight)
                || hittingPoint(new Point(otherUpperLeft.x + h.orientation.x, otherUpperLeft.y + h.orientation.y))
                || hittingPoint(new Point(otherLowerRight.x + h.orientation.inverseVec().x,
                        otherLowerRight.y + h.orientation.inverseVec().y)); /*
                || hittingVector(otherUpperLeft, h.orientation)
                || hittingVector(otherUpperLeft, h.normalOrientation())
                || hittingVector(otherLowerRight, h.orientation.inverseVec())
                || hittingVector(otherLowerRight, h.normalOrientation().inverseVec()); */ //Not necessary
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);

        g.drawLine((int) (upperLeft.x + position.x), (int) (upperLeft.y + position.y),
                (int) (upperLeft.x + orientation.x + position.x), (int) (upperLeft.y + orientation.y + position.y));
        g.drawLine((int) (upperLeft.x + position.x), (int) (upperLeft.y + position.y),
                (int) (upperLeft.x + normalOrientation().x + position.x), (int) (upperLeft.y + normalOrientation().y + position.y));
        g.drawLine((int) (lowerRight.x + position.x), (int) (lowerRight.y + position.y),
                (int) (lowerRight.x + orientation.inverseVec().x + position.x), (int) (lowerRight.y + orientation.inverseVec().y + position.y));
        g.drawLine((int) (lowerRight.x + position.x), (int) (lowerRight.y + position.y),
                (int) (lowerRight.x + normalOrientation().inverseVec().x + position.x), (int) (lowerRight.y + normalOrientation().inverseVec().y + position.y));
    }

}
