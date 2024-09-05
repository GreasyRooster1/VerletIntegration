package main.PhysicsObjects;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.println;

public class Spring {
    public VerletObject obj1;
    public VerletObject obj2;

    public double restLength;
    public double stiffness;

    public Spring(VerletObject obj1, VerletObject obj2, double restLength, double stiffness) {
        this.obj1 = obj1;
        this.obj2 = obj2;

        this.restLength = restLength;
        this.stiffness = stiffness;
    }

    public void apply(){
        Vector2D axis = obj1.positionCurrent.subtract(obj2.positionCurrent);
        double dist = axis.getNorm();

        double deformAmount = dist - restLength;

        double restorativeForce = stiffness * deformAmount;

        Vector2D force = axis.normalize().scalarMultiply(restorativeForce);


        obj1.applyForce(force.scalarMultiply(-1));
        obj2.applyForce(force);
    }
}
