import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.println;

public class Solver {
    public static VerletObject[] objects = {};

    public static Vector2D gravity = new Vector2D(0, 1000);

    static void update(double dt){
        applyGravity();
        applyConstraint();
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

    public static void applyConstraint(){
        for (VerletObject obj:objects){
            Vector2D toObj = obj.positionCurrent.subtract(BorderConstraint.center);
            double dist = toObj.getNorm();
            if(dist > BorderConstraint.radius-obj.radius){
                Vector2D n = new Vector2D(toObj.getX()/dist, toObj.getY()/dist);
                obj.positionCurrent = BorderConstraint.center.add(n.scalarMultiply(BorderConstraint.radius-obj.radius));
            }
        }
    }
}
