package main;

import main.Constraints.CircularBorderConstraint;
import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.append;
import static processing.core.PApplet.println;

public class Solver {
    public static VerletObject[] objects = {};
    public static Link[] links = {};
    public static Constraint[] constraints = {};

    public static int subSteps = 4;

    public static Vector2D gravity = new Vector2D(0, 1000);

    public static VerletObject newObject(double x, double y){
        VerletObject o = new VerletObject(x,y);
        objects = (VerletObject[]) append(objects,o);
        return o;
    }
    public static Link newLink(VerletObject obj1,VerletObject obj2,double targetDist){
        Link l = new Link(obj1,obj2,targetDist);
        links = (Link[]) append(links,l);
        return l;
    }
    public static void newConstraint(Constraint c){
        constraints = (Constraint[]) append(constraints,c);
    }

    static void setupConstraints(){
        newConstraint(new WindowBorderConstraint());
        newConstraint(new MouseConstraint());
    }

    static void update(double dt){
        double subDt = dt/subSteps;
        for(int i = 0; i < subSteps; i++) {
            applyGravity();
            applyConstraint();
            applyLinks();
            solveCollisions();
            updatePositions(subDt);
        }
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

    public static void applyLinks(){
        for (Link link:links){
            link.apply();
        }
    }

    public static void applyConstraint(){
        for (VerletObject obj:objects){
            for(Constraint c:constraints){
                c.apply(obj);
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
