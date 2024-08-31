import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.util.UUID;

import static processing.core.PApplet.println;

public class VerletObject {
    public final UUID ID;

    public Vector2D positionCurrent;
    public Vector2D positionOld;
    public Vector2D acceleration = Vector2D.ZERO;

    public int color;

    public double radius = 5;

    public VerletObject(double x, double y) {
        positionCurrent = new Vector2D(x, y);
        positionOld = new Vector2D(x, y);

        color = Color.getRainbow(Main.app.frameCount/100f);

        ID = UUID.randomUUID();
    }

    public void updatePosition(double dt){
        Vector2D velocity = positionCurrent.subtract(positionOld);

        positionOld = new Vector2D(1,positionCurrent);
        positionCurrent = positionCurrent.add(velocity.add(acceleration.scalarMultiply(dt*dt)));

        acceleration = new Vector2D(0,0);
    }

    public void accelerate(Vector2D acc) {
        acceleration = acceleration.add(acc);
    }
}
