package main.Constraints;

import main.Main;
import main.PhysicsObjects.VerletObject;

import main.Constraint;
import main.Util.FastVec2;

public class CircularBorderConstraint extends Constraint{
    public static FastVec2 center = new FastVec2(250,250);
    public static double radius = 250;

    public void apply(VerletObject obj){
        FastVec2 toObj = obj.positionCurrent.sub(CircularBorderConstraint.center);
        double dist = toObj.getLength();
        if(dist > CircularBorderConstraint.radius-obj.radius){
            FastVec2 n = new FastVec2(toObj.x/dist, toObj.y/dist);
            obj.positionCurrent = CircularBorderConstraint.center.add(n.scalarMult(CircularBorderConstraint.radius-obj.radius));
        }
    }

    public void render(){
        Main.app.noFill();
        Main.app.stroke(255,127);
        Main.app.ellipse(CircularBorderConstraint.center.x, CircularBorderConstraint.center.y, CircularBorderConstraint.radius);
    }
}
