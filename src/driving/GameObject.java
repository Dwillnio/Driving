
package driving;

import driving.Physics.Hitbox;

public abstract class GameObject {

    protected final Point position;
    protected Vector orientation;
    protected Hitbox hitbox;
    protected Vector velocity;
    
    protected int collisionCooldown;
    
    public GameObject(Point position) {
        this.position = position;
        collisionCooldown = 0;
    }
    
    public void increasecollisionCooldown(int i) {
        collisionCooldown += i;
    }
    
    public boolean collisionOnCooldown() {
        return collisionCooldown != 0;
    }
    
    public Point getPosition() {
        return position;
    }
    
    public Vector getOrientation(){
        return orientation;
    }
    
    public Hitbox getHitbox(){
        return hitbox;
    }
    
    public Vector getVelocity() {
        return velocity;
    }
}
