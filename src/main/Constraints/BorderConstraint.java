package main.Constraints;

import main.VerletObject;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import main.Constraint;

public class BorderConstraint extends Constraint{
    public static Vector2D center = new Vector2D(250,250);
    public static double radius = 200;

    public void apply(VerletObject obj){
        Vector2D toObj = obj.positionCurrent.subtract(BorderConstraint.center);
        double dist = toObj.getNorm();
        if(dist > BorderConstraint.radius-obj.radius){
            Vector2D n = new Vector2D(toObj.getX()/dist, toObj.getY()/dist);
            obj.positionCurrent = BorderConstraint.center.add(n.scalarMultiply(BorderConstraint.radius-obj.radius));
        }
    }
}
