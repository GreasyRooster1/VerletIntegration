import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.println;

public class VerletObject {
    public Vector2D positionCurrent;
    public Vector2D positionOld;
    public Vector2D acceleration = new Vector2D(0,0);

    public double radius = 15;

    public VerletObject(double x, double y) {
        positionCurrent = new Vector2D(x, y);
        positionOld = new Vector2D(x, y);
    }

    public void updatePosition(double dt){
        Vector2D velocity = positionCurrent.subtract(positionOld);

        positionOld = positionCurrent;
        positionCurrent = positionCurrent.add(velocity.add(acceleration.scalarMultiply(dt*dt)));
        println(acceleration);
        acceleration = new Vector2D(0,0);
    }

    public void accelerate(Vector2D acc){
        acceleration.add(acc);
    }
}
