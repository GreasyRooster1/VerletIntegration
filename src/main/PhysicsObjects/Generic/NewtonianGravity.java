package main.PhysicsObjects.Generic;

import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;

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
            FastVec2 axis = position.sub(obj.positionCurrent);
            double dist = axis.getLength();
            double force =  (G*mass*obj.mass)/(dist*dist);

            obj.applyForce(axis.normalized().scalarMult(min(force,MAX_FORCE)));
        }
    }
}
