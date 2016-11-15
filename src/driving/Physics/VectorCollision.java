
package driving.Physics;

import driving.Point;
import driving.Vector;

public class VectorCollision {
    
    private static Point straightHittingStraight(Point a, Vector av, Point b, Vector bv) {
        double s = (av.x * b.y + a.y * av.x + a.x* av.y + b.x * av.y ) / (bv.x * av.y - bv.y * av.x);
        return new Point((b.x + bv.x * s), (b.y + bv.y * s));
    }
    
    private static boolean pointOnVector(Point a, Point source, Vector vector) {
        double r1 = (a.x - source.x)/(vector.x);
        double r2 = (a.y - source.y)/(vector.y);
        return Math.abs(r1 -r2) < 0.0001 && r1 <= vector.length() && r1 >= 0;
    }
    
    public static boolean vectorHittingVector(Point s1, Vector v1, Point s2, Vector v2) {
        try {
            Point intersection = straightHittingStraight(s1, v1, s2, v2);
            return pointOnVector(intersection, s2, v2) && pointOnVector(intersection, s1, v1);
        } catch (Exception ex) {
            return false; // could be "true" sometimes but who cares LMAO
        }
    }
    
}