import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Solver {
    public static VerletObject[] objects = {};

    public static  Vector2D gravity = new Vector2D(0, -1000);

    static void update(double dt){
        applyGravity();
        updatePositions(dt);
    }

    static void updatePositions(double dt){
        for (VerletObject obj:objects){
            obj.updatePosition(dt);
        }
    }

    static void applyGravity(){
        for (VerletObject obj:objects){
            obj.accelerate(gravity);
        }
    }
}
