package main.PhysicsObjects;

import main.Color;
import main.Main;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

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

    public double radius;
    public static final double DEFAULT_RADIUS =3;
    public double drag = 0;

    public VerletObject(double x, double y) {
        positionCurrent = new Vector2D(x, y);
        positionOld = new Vector2D(x, y);
        initalPosition = new Vector2D(x, y);

        color = Color.getRainbow(Main.app.frameCount/100f);

        radius = DEFAULT_RADIUS;//Main.app.random(2f, (float) DEFAULT_RADIUS);

        ID = UUID.randomUUID();
    }

    public void updatePosition(double dt){
        if(isStatic){
            acceleration = Vector2D.ZERO;
            positionCurrent = initalPosition;
            return;
        }
        Vector2D velocity = getVelocity();

        positionOld = new Vector2D(1,positionCurrent);
        positionCurrent = positionCurrent.add(velocity.add(acceleration.scalarMultiply(dt*dt)));

        //positionCurrent = new Vector2D(positionCurrent.getX()%Main.app.width,positionCurrent.getY()%Main.app.height);

        acceleration = new Vector2D(0,0);
    }

    public Vector2D getVelocity(){
        return positionCurrent.subtract(positionOld);
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
