package main.Constraints;

import main.Constraint;
import main.Main;
import main.PhysicsObjects.VerletObject;
import main.Util.FastVec2;

import static java.lang.Math.max;

public class MouseConstraint extends Constraint{
    public static double radius = 30;

    public void apply(VerletObject obj){
        if(Main.app.mousePressed){
            return;
        }

        FastVec2 center = new FastVec2(Main.app.mouseX,Main.app.mouseY);
        FastVec2 collisionAxis = center.sub(obj.positionCurrent);
        double dist = max(collisionAxis.getLength(),0.001);
        if(dist < radius+obj.radius){
            FastVec2 n =  new FastVec2(collisionAxis.x/dist, collisionAxis.y/dist);
            double delta = radius+obj.radius - dist;
            obj.positionCurrent = obj.positionCurrent.sub(n.scalarMult(delta));

        }
    }

    public void render(){
        if(Main.app.mousePressed){
            Main.app.fill(255,50);
        }else {
            Main.app.fill(255,127);
        }
        Main.app.noStroke();
        Main.app.ellipse(Main.app.mouseX,Main.app.mouseY, radius);
    }
}
