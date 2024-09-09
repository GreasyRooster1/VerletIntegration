package main.PhysicsObjects;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class PhysicsGeneric {
    public Vector2D position;

    public PhysicsGeneric(double x, double y) {
        position = new Vector2D(x, y);
    }

    public void update(double dt){

    }

    public PhysicsGeneric setPosition(double x, double y) {
        position = new Vector2D(x, y);
        return this;
    }
}
