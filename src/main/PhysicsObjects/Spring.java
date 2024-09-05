package main.PhysicsObjects;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Spring {
    public VerletObject obj1;
    public VerletObject obj2;

    public double restLength;
    public double stiffness;

    public double springMultiplier = 100_000;

    public Spring(VerletObject obj1, VerletObject obj2, double restLength, double stiffness) {
        this.obj1 = obj1;
        this.obj2 = obj2;

        this.restLength = restLength;
        this.stiffness = stiffness;
    }

    public void apply(){
        Vector2D axis = obj1.positionCurrent.subtract(obj2.positionCurrent);
        double dist = axis.getNorm();
        Vector2D n = new Vector2D(axis.getX()/dist, axis.getY()/dist);
        double delta = restLength - dist;
        obj1.accelerate(n.scalarMultiply(delta*0.5*springMultiplier));
        obj2.accelerate(n.scalarMultiply(delta*0.5*springMultiplier).negate());
    }
}
