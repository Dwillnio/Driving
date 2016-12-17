

package driving.Physics;

import driving.GameObject;
import driving.Vector;

public class Collision {

    private final GameObject a;
    private final GameObject b;
    
    public Collision(GameObject a, GameObject b) {
        this.a = a;
        this.b = b;
    }

    public GameObject getFirst() {
        return a;
    }

    public GameObject getSecond() {
        return b;
    }
    
}
