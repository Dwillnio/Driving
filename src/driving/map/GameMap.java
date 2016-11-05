package driving.map;

import driving.Drawable;
import java.awt.Color;
import java.awt.Graphics;

public class GameMap implements Drawable{

    private GroundNode firstNode;

    public GameMap() {

    }

    public GroundNode first() {
        return firstNode;
    }
    
    public GroundNode last() {
        if (firstNode == null) {
            return null;
        }

        GroundNode n = firstNode.next;
        if (n == null) {
            return firstNode;
        }

        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    public int size() {
        if (firstNode == null) {
            return 0;
        }

        GroundNode n = firstNode.next;
        if (n == null) {
            return 1;
        }

        int counter = 2;
        while (n.next != null) {
            n = n.next;
            counter++;
        }
        return counter;
    }

    public void add(GroundNode gr) {
        if (firstNode == null) {
            firstNode = gr;
        } else {
            last().next = gr;
        }
    }

    public GroundNode get(int index) {
        if (index == 0) {
            return firstNode;
        }
        int counter = 1;
        GroundNode n = firstNode.next;
        while (counter != index) {
            n = n.next;
            counter++;
        }
        return n;
    }

    public void pop() {
        int size = size();
        if (size == 1) {
            firstNode = null;
        } else {
            get(size - 2).next = null;
        }
    }

    public void remove(int index) {
        if (index == 0) {
            if (firstNode.next != null) {
                firstNode = firstNode.next;
            } else {
                firstNode = null;
            }
        } else {
            GroundNode n = get(index - 1);
            n.next = n.next.next;
        }
    }

    public void insert(int index, GroundNode n) {
        if (index == 0) {
            n.next = firstNode;
            firstNode = n;
        } else {
            GroundNode previous = get(index - 1);
            n.next = previous.next;
            previous.next = n;
        }
    }

    public void replace(int index, GroundNode n) {
        if (index == 0) {
            n.next = firstNode.next;
            firstNode = n;
        } else {
            GroundNode previous = get(index - 1);
            n.next = previous.next.next;
            previous.next = n;
        }
    }
    
    @Override
    public String toString() {
        if(firstNode == null) {
            return "{}";
        }
        
        GroundNode n = firstNode;
        String s = "{";
        while(n != null) {
            s += "[" + (int)n.x + "|" + (int)n.y + "],";
        }
        
        return s + "}";
    }
    
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        if(firstNode != null) {
            GroundNode n = firstNode;
            while(n.next != null) {
                graphics.drawLine((int)n.x, (int)n.y, (int)n.next.x, (int)n.next.y);
                n = n.next;
            }
        }
    }
}
