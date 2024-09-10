package main.PhysicsObjects.Generic;

import main.PhysicsObjects.VerletObject;
import main.Util.FastVec2;

import static processing.core.PApplet.println;

public class Spring {
    public VerletObject obj1;
    public VerletObject obj2;

    public double restLength;
    public double stiffness;
    public double damping = 0.01;

    public Spring(VerletObject obj1, VerletObject obj2, double restLength, double stiffness) {
        this.obj1 = obj1;
        this.obj2 = obj2;

        this.restLength = restLength;
        this.stiffness = stiffness;
    }

    public void apply(){
        FastVec2 axis = obj1.positionCurrent.sub(obj2.positionCurrent);
        double dist = axis.getLength();

        double deformAmount = dist - restLength;

        double restorativeForce = stiffness * deformAmount;

        FastVec2 force = axis.normalized().scalarMult(restorativeForce);


        obj1.applyForce(force.scalarMult(-1));
        obj2.applyForce(force);

        obj1.applyForce(force.scalarMult(damping));
        obj2.applyForce(force.scalarMult(-damping));
    }
}
