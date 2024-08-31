import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class VerletObject {
    public Vector2D positionCurrent = new Vector2D(250,250);
    public Vector2D positionOld = new Vector2D(250,250);
    public Vector2D acceleration = new Vector2D(0,0);

    public void updatePosition(double dt){
        Vector2D newVelocity = positionCurrent.subtract(positionOld);

        positionOld = positionCurrent;
        positionCurrent = positionCurrent.add(newVelocity).add(acceleration.scalarMultiply(dt*dt));

        acceleration = new Vector2D(0,0);
    }

    public void accelerate(Vector2D acc){
        acceleration.add(acc);
    }
}
