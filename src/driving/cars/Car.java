package driving.cars;

import driving.Drawable;
import driving.Driving;
import driving.Renderer.Body;
import driving.Updateable;
import driving.Vector;
import java.awt.Color;
import java.awt.Graphics;

public class Car implements Drawable, Updateable {

    private double x;
    private double y;

    private final Vector velocity;
    private final Vector orientation;

    private Body body;

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

    public Car(double x, double y, Body body) {
        this.x = x;
        this.y = y;
        this.body = body;

        velocity = new Vector(0, 0);
        orientation = new Vector(1, 0);
        onGas = false;
    }

    private void move(double frameFactor) {
        x += velocity.x * frameFactor;
        y += velocity.y * frameFactor;
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
        }
        if (rotateDown) {
            orientation.rotate(Math.PI / 64 * frameFactor);
            body.rotate(Math.PI / 64 * frameFactor);
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
        double frameFactor = frameTime / Driving.INTENDED_FRAMETIME;

        if (onGas) {
            accelerate(frameFactor);
        } else if (velocity.length() > 0.2 * frameFactor) {
            deccelerate(frameFactor);
        } else {
            velocity.scale(0.999 * frameFactor);
        }
        rotate(frameFactor);
        rotateVelocity(frameFactor);
        move(frameFactor);
    }

    public void turn(double radians) {
        velocity.rotate(radians);
        body.rotate(radians);
    }
    
    public Vector getOrientation() {
        return orientation;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector getVelocity() {
        return velocity;
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
        /*
        graphics.setColor(Color.red);

        graphics.fillRect((int) x -25, (int) y, 50, 20);
         */
        graphics.setColor(Color.GREEN);
        graphics.drawLine((int) x, (int) y, (int) (x + (25 * velocity.x)), (int) (y + (25 * velocity.y)));

        graphics.setColor(Color.RED);
        graphics.drawLine((int) x, (int) y, (int) (x + (25 * orientation.x)), (int) (y + (25 * orientation.y)));
    }
}
