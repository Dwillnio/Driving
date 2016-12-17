package driving.Physics;

import driving.Vector;

public class CollisionHandler {

    public CollisionHandler() {

    }

    public void applyCollisionToVec(Vector vec, Vector link, Collision c) {
        double nLength = Math.abs(vec.dotProduct(link) / link.length());
        Vector v = link.unitVector();
        v.scale(1.25 * nLength);

        Vector independentRepulsion = link.unitVector();
        independentRepulsion.scale(0.2);
        
        vec.subtract(v);
        vec.subtract(independentRepulsion);
    }

    public void handleCollision(Collision collision) {
        if (collision != null) {
            if (!collision.getFirst().collisionOnCooldown()) {
                applyCollisionToVec(collision.getFirst().getVelocity(), 
                        new Vector(collision.getSecond().getPosition().x - collision.getFirst().getPosition().x, 
                        collision.getSecond().getPosition().y - collision.getFirst().getPosition().y), collision);
                collision.getFirst().increasecollisionCooldown(5);
            }
            if (!collision.getSecond().collisionOnCooldown()) {
                applyCollisionToVec(collision.getSecond().getVelocity(), 
                        new Vector(collision.getFirst().getPosition().x - collision.getSecond().getPosition().x, 
                        collision.getFirst().getPosition().y - collision.getSecond().getPosition().y), collision);
                
                collision.getSecond().increasecollisionCooldown(5);
            }
        }
    }

}
