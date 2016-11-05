

package driving.Physics;

import driving.Driving;
import driving.Updateable;

public class Collision implements Updateable {

    private Driving game;
    
    public Collision(Driving game) {
        this.game = game;
    }
    
    @Override
    public void update() {
        update(Driving.INTENDED_FRAMETIME); //
    }
    
    @Override
    public void update(double frameTime) {
        
    }
    
    
}
