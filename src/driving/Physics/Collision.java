

package driving.Physics;

import driving.Vector;

public class Collision {

    private final Hitbox firstHitbox;
    private final Hitbox secondHitbox;
    private final Vector firstToSecond;
    
    public Collision(Hitbox h1, Hitbox h2) {
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
