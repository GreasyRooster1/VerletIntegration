import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.append;
import static processing.core.PApplet.println;

public class Solver {
    public static VerletObject[] objects = {};

    public static Vector2D gravity = new Vector2D(0, 1000);

    public static void newObject(double x, double y){
        objects = (VerletObject[]) append(Solver.objects,new VerletObject(x,y));
    }

    static void update(double dt){
        applyGravity();
        solveCollisions();
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

    public static void solveCollisions(){
        for (VerletObject obj1:objects) {
            for (VerletObject obj2 : objects) {
                if(obj1.ID.equals(obj2.ID)){
                    continue;
                }
                Vector2D collisionAxis = obj1.positionCurrent.subtract(obj2.positionCurrent);
                double dist = collisionAxis.getNorm();
                if(dist < obj1.radius+obj2.radius){
                    Vector2D n =  new Vector2D(collisionAxis.getX()/dist, collisionAxis.getY()/dist);
                    double delta = obj1.radius+obj2.radius - dist;
                    obj1.positionCurrent = obj1.positionCurrent.add(n.scalarMultiply(delta).scalarMultiply(.5));
                    obj2.positionCurrent = obj2.positionCurrent.subtract(n.scalarMultiply(delta).scalarMultiply(.5));

                }
            }
        }
    }
}
