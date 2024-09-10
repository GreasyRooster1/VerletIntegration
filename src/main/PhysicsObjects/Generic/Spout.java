package main.PhysicsObjects.Generic;

import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;

public class Spout extends PhysicsGeneric {
    public FastVec2 shootAcceleration;
    public double safeRadius = 10;

    public Spout(double x,double y) {
        super(x,y);
    }

    public void update(double dt){
        for(VerletObject obj: Solver.objects){
            FastVec2 collisionAxis = obj.positionCurrent.sub(position);
            double dist = collisionAxis.getLength();
            if(dist < safeRadius+obj.radius){
                return;
            }
        }
        VerletObject obj = Solver.newObject(position.x, position.y);
        obj.setAcceleration(shootAcceleration);
    }

    public Spout setShootAcceleration(double x, double y) {
        shootAcceleration = new FastVec2(x, y);
        return this;
    }

    public Spout setSafeRadius(double r) {
        safeRadius = r;
        return this;
    }
}
