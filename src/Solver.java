import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Solver {
    public VerletObject[] objects = {};

    public Vector2D gravity = new Vector2D(0, -1000);

    void update(double dt){
        applyGravity();
        updatePositions(dt);
    }

    void updatePositions(double dt){
        for (VerletObject obj:objects){
            obj.updatePosition(dt);
        }
    }

    void applyGravity(){
        for (VerletObject obj:objects){
            obj.accelerate(gravity);
        }
    }
}
