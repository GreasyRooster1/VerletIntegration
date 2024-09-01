package main.Constraints;

import main.Main;
import main.PhysicsObjects.VerletObject;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import main.Constraint;

public class CircularBorderConstraint extends Constraint{
    public static Vector2D center = new Vector2D(250,250);
    public static double radius = 250;

    public void apply(VerletObject obj){
        Vector2D toObj = obj.positionCurrent.subtract(CircularBorderConstraint.center);
        double dist = toObj.getNorm();
        if(dist > CircularBorderConstraint.radius-obj.radius){
            Vector2D n = new Vector2D(toObj.getX()/dist, toObj.getY()/dist);
            obj.positionCurrent = CircularBorderConstraint.center.add(n.scalarMultiply(CircularBorderConstraint.radius-obj.radius));
        }
    }

    public void render(){
        Main.app.noFill();
        Main.app.stroke(255,127);
        Main.app.ellipse(CircularBorderConstraint.center.getX(), CircularBorderConstraint.center.getY(), CircularBorderConstraint.radius);
    }
}
