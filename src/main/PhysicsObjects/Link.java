package main.PhysicsObjects;


import main.Util.FastVec2;

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
        FastVec2 axis = obj1.positionCurrent.sub(obj2.positionCurrent);
        double dist = axis.getLength();
        FastVec2 n = new FastVec2(axis.x/dist, axis.y/dist);
        double delta = targetDist - dist;
        obj1.positionCurrent = obj1.positionCurrent.add(n.scalarMult(delta*0.5));
        obj2.positionCurrent = obj2.positionCurrent.sub(n.scalarMult(delta*0.5));
    }
}
