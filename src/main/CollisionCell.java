package main;

import main.PhysicsObjects.VerletObject;

public class CollisionCell {
    public int[] objectIndices = {};
    public int x,y;
    public static final double CELL_SIZE = VerletObject.MAX_RADIUS *2;

    CollisionCell(int x, int y){
        this.x=x;
        this.y=y;
    }
}
