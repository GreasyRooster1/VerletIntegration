package main;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import processing.core.PApplet;

import java.util.UUID;

import static processing.core.PApplet.println;

public class VerletObject {
    public final UUID ID;

    public final Vector2D initalPosition;
    public Vector2D positionCurrent;
    public Vector2D positionOld;
    public Vector2D acceleration = Vector2D.ZERO;
    public boolean isStatic = false;

    public int color;

    public double radius = 10;

    public VerletObject(double x, double y) {
        positionCurrent = new Vector2D(x, y);
        positionOld = new Vector2D(x, y);
        initalPosition = new Vector2D(x, y);

        color = Color.getRainbow(Main.app.frameCount/100f);
        radius = Main.app.random(2,6);

        ID = UUID.randomUUID();
    }

    public void updatePosition(double dt){
        if(isStatic){
            acceleration = Vector2D.ZERO;
            positionCurrent = initalPosition;
            return;
        }
        Vector2D velocity = positionCurrent.subtract(positionOld);

        positionOld = new Vector2D(1,positionCurrent);
        positionCurrent = positionCurrent.add(velocity.add(acceleration.scalarMultiply(dt*dt)));

        acceleration = new Vector2D(0,0);
    }

    public void accelerate(Vector2D acc) {
        acceleration = acceleration.add(acc);
    }

    public VerletObject setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }
    public VerletObject setRadius(double radius) {
        this.radius = radius;
        return this;
    }
    public VerletObject setColor(float r, float g, float b) {
        this.color = Main.app.color(r,g,b);
        return this;
    }

    public VerletObject setAcceleration(Vector2D vector2D) {
        acceleration = vector2D;
        return this;
    }
}
