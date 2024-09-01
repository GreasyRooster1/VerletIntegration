package main.Constraints;

import main.Constraint;
import main.Main;
import main.VerletObject;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static java.lang.Math.max;

public class MouseConstraint extends Constraint{
    public static double radius = 30;

    public void apply(VerletObject obj){
        if(Main.app.mousePressed){
            return;
        }

        Vector2D center = new Vector2D(Main.app.mouseX,Main.app.mouseY);
        Vector2D collisionAxis = center.subtract(obj.positionCurrent);
        double dist = max(collisionAxis.getNorm(),0.001);
        if(dist < radius+obj.radius){
            Vector2D n =  new Vector2D(collisionAxis.getX()/dist, collisionAxis.getY()/dist);
            double delta = radius+obj.radius - dist;
            obj.positionCurrent = obj.positionCurrent.subtract(n.scalarMultiply(delta));

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
