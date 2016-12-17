

package driving.Physics;

import driving.GameObject;
import driving.Vector;

public class Collision {

    private final GameObject a;
    private final GameObject b;
    private final Hitbox firstHitbox;
    private final Hitbox secondHitbox;
    private final Vector firstToSecond;
    
    public Collision(GameObject a, GameObject b, Hitbox h1, Hitbox h2) {
        this.a = a;
        this.b = b;
        firstHitbox = h1;
        secondHitbox = h2;
        firstToSecond = new Vector(h2.getPosition().x - h1.getPosition().x, h2.getPosition().y - h1.getPosition().y);
    }

    public Hitbox getFirstHitbox() {
        return firstHitbox;
    }

    public Hitbox getSecondHitbox() {
        return secondHitbox;
    }

    public Vector getFirstToSecond() {
        return firstToSecond;
    }
    
}
