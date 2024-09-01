package main;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Link {
    public VerletObject obj1;
    public VerletObject obj2;

    public double targetDist;

    public Link(VerletObject obj1, VerletObject obj2,double targetDist) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.targetDist = targetDist;
    }

    public void apply(){
        Vector2D axis = obj1.positionCurrent.subtract(obj2.positionCurrent);
        double dist = axis.getNorm();
        Vector2D n = new Vector2D(axis.getX()/dist, axis.getY()/dist);
        double delta = targetDist - dist;
        obj1.positionCurrent = obj1.positionCurrent.add(n.scalarMultiply(delta*0.5));
        obj2.positionCurrent = obj2.positionCurrent.subtract(n.scalarMultiply(delta*0.5));
    }
}
