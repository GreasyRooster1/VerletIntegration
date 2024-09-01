package main;

import main.PhysicsObjects.VerletObject;

public class World {
    public static void create() {
        chainWorld();
    }

    private static void chainWorld(){
        int objCount = 1;
        double endRadius = VerletObject.DEFAULT_RADIUS;
        float step = (float) VerletObject.DEFAULT_RADIUS*2;
        float chainHeight = 250;

        Solver.newObject(50,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        for (float i = 50+step; i < 450f; i+=step) {
            Solver.newObject(i,chainHeight).setColor(255,255,255);
            if(objCount==1) {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step/2+endRadius);
            }else {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step);
            }
            objCount++;
        }

        VerletObject chainEnd = Solver.newObject(450,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        Solver.newLink(chainEnd,Solver.objects[objCount-1],step);
    }
}
