package main.Constraints;

import main.Constraint;
import main.Main;
import main.PhysicsObjects.VerletObject;
import main.Util.FastVec2;

import static java.lang.Math.max;
import static java.lang.Math.min;


public class WindowBorderConstraint extends Constraint{

    public void apply(VerletObject obj){
        obj.positionCurrent = new FastVec2(
                min(max(obj.positionCurrent.x,obj.radius),Main.app.width-obj.radius),
                min(max(obj.positionCurrent.y,obj.radius),Main.app.height-obj.radius));
    }

    public void render(){
        Main.app.stroke(127);
        Main.app.strokeWeight(4);
        Main.app.noFill();
        Main.app.rect(0,0,Main.app.width,Main.app.height);
    }
}
