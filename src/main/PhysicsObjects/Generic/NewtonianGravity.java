package main.PhysicsObjects.Generic;

import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static java.lang.Math.min;

public class NewtonianGravity extends PhysicsGeneric {
    public static final double G = 1;
    public static final double MAX_FORCE = 500;

    public double mass = 10_000_000;

    public NewtonianGravity(double x, double y) {
        super(x, y);
    }

    public void update(double dt){
        for(VerletObject obj: Solver.objects){
            Vector2D axis = position.subtract(obj.positionCurrent);
            double dist = axis.getNorm();
            double force =  (G*mass*obj.mass)/(dist*dist);

            obj.applyForce(axis.normalize().scalarMultiply(min(force,MAX_FORCE)));
        }
    }
}
