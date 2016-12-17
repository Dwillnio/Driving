
package driving;

import driving.Physics.Hitbox;

public abstract class GameObject {

    protected final Point position;
    protected Vector orientation;
    protected Hitbox hitbox;
    
    public GameObject(Point position) {
        this.position = position;
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
}
