

package driving.Physics;

import driving.Driving;
import driving.Updateable;

public class CollisionDetector implements Updateable {

    private final Driving game;
    private final CollisionHandler collisionHandler;
    
    public CollisionDetector(Driving game) {
        this.game = game;
        collisionHandler = new CollisionHandler();
    }
    
    @Override
    public void update() {
        update(Driving.INTENDED_FRAMETIME); //
    }
    
    @Override
    public void update(double frameTime) {
        Collision c;
        
        for(int x = 0; x < game.getCollideables().size(); x++) {
            
            for(int y = 0; y < game.getCollideables().size(); y++) {
                if(y != x) {
                    
                    c = game.getCollideables().get(x).hittingBox(game.getCollideables().get(y));
                    
                    if(c != null) {
                        collisionHandler.handleCollision(c);
                    }
                }
            }
        }
    }
    
    
}
