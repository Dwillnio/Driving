

package driving.ui;

import driving.Drawable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class DrawingBoard extends JPanel{
    
    private List<Drawable> drawables;
    
    public DrawingBoard(List<Drawable> drawables) {
        this.drawables = drawables;
    }
    
    public void updateDrawables(List<Drawable> drawables) {
        this.drawables = drawables;
    }
    
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        for(Drawable todraw: drawables) {
            todraw.draw(graphics);
        }
    }
    
    public void update(){
        super.repaint();
    }
    
}
