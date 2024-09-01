package main.PhysicsObjects;

import main.Solver;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Spout extends PhysicsGeneric{
    public Vector2D shootAcceleration;
    public double safeRadius = 5;

    public Spout(double x,double y) {
        super(x,y);
    }

    public void update(double dt){
        for(VerletObject obj: Solver.objects){
            Vector2D collisionAxis = obj.positionCurrent.subtract(position);
            double dist = collisionAxis.getNorm();
            if(dist < safeRadius+obj.radius){
                return;
            }
        }
        VerletObject obj = Solver.newObject(position.getX(), position.getY());
        obj.setAcceleration(shootAcceleration);
    }

    public Spout setShootAcceleration(double x, double y) {
        shootAcceleration = new Vector2D(x, y);
        return this;
    }

    public Spout setSafeRadius(double r) {
        safeRadius = r;
        return this;
    }
}
