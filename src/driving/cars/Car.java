package driving.cars;

import driving.Drawable;
import driving.Driving;
import driving.GameObject;
import driving.Physics.Hitbox;
import driving.Point;
import driving.Renderer.Body;
import driving.Updateable;
import driving.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class Car extends GameObject implements Drawable, Updateable  {

    private final Body body;

    //Flags
    private boolean onGas;
    private boolean velocityTurningUp;
    private boolean velocityTurningDown;
    private boolean rotateUp;
    private boolean rotateDown;

    public void setVelocityTurningUp(boolean velocityTurningLeft) {
        this.velocityTurningUp = velocityTurningLeft;
    }

    public void setVelocityTurningDown(boolean velocityTurningRight) {
        this.velocityTurningDown = velocityTurningRight;
    }

    public boolean isRotatingUp() {
        return rotateUp;
    }

    public void setRotatingUp(boolean turningLeft) {
        this.rotateUp = turningLeft;
    }

    public boolean isRotatingDown() {
        return rotateDown;
    }

    public void setRotatingDown(boolean turningRight) {
        this.rotateDown = turningRight;
    }

    public boolean isVelocityTurningUp() {
        return velocityTurningUp;
    }

    public boolean isVelocityTurningDown() {
        return velocityTurningDown;
    }
    
    public void setHitbox(Hitbox h) {
        this.hitbox = h;
    }
    
    public Car(Point pos, Body body) {
        this(pos, body, null);
    }

    public Car(Point pos, Body body, Hitbox hitbox) {
        super(pos);
        this.body = body;
        super.hitbox = hitbox;

        velocity = new Vector(0, 0);
        super.orientation = new Vector(1, 0);
        onGas = false;
    }

    private void move(double frameFactor) {
        position.x += velocity.x * frameFactor;
        position.y += velocity.y * frameFactor;
        body.move(velocity.x * frameFactor, velocity.y * frameFactor);
    }

    private void rotateVelocity(double frameFactor) {
        if (isVelocityTurningUp()) {
            velocity.rotate(-Math.PI / 256 * frameFactor);
        }
        if (isVelocityTurningDown()) {
            velocity.rotate(Math.PI / 256 * frameFactor);
        }
    }

    private void rotate(double frameFactor) {
        if (rotateUp) {
            orientation.rotate(-Math.PI / 64 * frameFactor);
            body.rotate(-Math.PI / 64 * frameFactor);
            hitbox.rotate(-Math.PI / 64 * frameFactor);
        }
        if (rotateDown) {
            orientation.rotate(Math.PI / 64 * frameFactor);
            body.rotate(Math.PI / 64 * frameFactor);
            hitbox.rotate(Math.PI / 64 * frameFactor);
        }
    }

    private void accelerate(double frameFactor) {
        velocity.addScaleVector(orientation, 0.02 * frameFactor);
    }

    private void deccelerate(double frameFactor) {
        velocity.increase(-0.01 * frameFactor);
    }

    @Override
    public void update() {
        update((int) Driving.INTENDED_FRAMETIME);
    }

    @Override
    public void update(double frameTime) {
        if(collisionCooldown > 0) {
            collisionCooldown--;
        }
        
        double frameFactor = frameTime / Driving.INTENDED_FRAMETIME;

        if (onGas) {
            accelerate(frameFactor);
        } else if (velocity.length() > 0.2 * frameFactor) {
            deccelerate(frameFactor);
        } else {
            velocity.scale(0.99);
        }
        rotate(frameFactor);
        rotateVelocity(frameFactor);
        move(frameFactor);
    }

    public void turn(double radians) {
        velocity.rotate(radians);
        body.rotate(radians);
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public boolean onGas() {
        return onGas;
    }

    public void setGas(boolean onGas) {
        this.onGas = onGas;
    }

    @Override
    public void draw(Graphics graphics) {
        body.draw(graphics);
        hitbox.draw(graphics);
        /*
        graphics.setColor(Color.red);

        graphics.fillRect((int) x -25, (int) y, 50, 20);
         */
        graphics.setColor(Color.GREEN);
        graphics.drawLine((int) position.x, (int) position.y,
                (int) (position.x + (25 * velocity.x)),
                (int) (position.y + (25 * velocity.y)));

        graphics.setColor(Color.RED);
        graphics.drawLine((int) position.x, (int) position.y,
                (int) (position.x + (25 * orientation.x)),
                (int) (position.y + (25 * orientation.y)));
    }
}
