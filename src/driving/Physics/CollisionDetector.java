

package driving.Physics;

import driving.Driving;
import driving.Updateable;

public class CollisionDetector implements Updateable {

    private Driving game;
    
    public CollisionDetector(Driving game) {
        this.game = game;
    }
    
    @Override
    public void update() {
        update(Driving.INTENDED_FRAMETIME); //
    }
    
    @Override
    public void update(double frameTime) {
        for(int x = 0; x < game.getCollideables().size(); x++) {
            
            for(int y = 0; y < game.getCollideables().size(); y++) {
                if(y != x) {
                    
                    if(game.getCollideables().get(x).hittingBox(game.getCollideables().get(y))) {
                        System.out.println("HIT!");
                    }
                        
                }
            }
            
        }
    }
    
    
}
