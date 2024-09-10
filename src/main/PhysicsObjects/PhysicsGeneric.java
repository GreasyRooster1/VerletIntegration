package main.PhysicsObjects;

import main.Util.FastVec2;

public class PhysicsGeneric {
    public FastVec2 position;

    public PhysicsGeneric(double x, double y) {
        position = new FastVec2(x, y);
    }

    public void update(double dt){

    }

    public PhysicsGeneric setPosition(double x, double y) {
        position = new FastVec2(x, y);
        return this;
    }
}
