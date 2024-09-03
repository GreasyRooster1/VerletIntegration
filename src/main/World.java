package main;

import main.Constraints.CircularBorderConstraint;
import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.Spout;
import main.PhysicsObjects.VerletObject;

public class World {
    public static void create() {
        boxWorld();
    }

    private static void boxWorld(){
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int width = 4;
        int height = 4;
        double xPos = 250;
        double yPos = 250;
        double stepSize = VerletObject.DEFAULT_RADIUS*2;

        int objCount = 0;
        int xCount = 0;

        for(int x=0;x<width;x++){
            int yCount = 0;
            for(int y=0;y<height;y++){
                Solver.newObject(xPos+(x*stepSize),yPos+(y*stepSize));
                if(objCount!=0&&yCount!=0) {
                    Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], stepSize);
                }
                yCount++;
                objCount++;
            }
            if(xCount!=0) {
                for(int y=0;y<height;y++){
                    Solver.newLink(Solver.objects[objCount-y], Solver.objects[objCount-(y+(xCount*height))], stepSize);
                }
            }
            xCount++;
        }
    }

    private static void chainWorld(){
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int objCount = 1;
        double endRadius = VerletObject.DEFAULT_RADIUS;
        float step = (float) VerletObject.DEFAULT_RADIUS*2;
        float chainHeight = Main.app.height/2f;
        float chainEnd = Main.app.width-Main.app.width/10f;
        float chainStart = Main.app.width/10f;

        Solver.newObject(chainStart,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        for (float i = chainStart+step; i < chainEnd; i+=step) {
            Solver.newObject(i,chainHeight).setColor(255,255,255);
            if(objCount==1) {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step/2+endRadius);
            }else {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step);
            }
            objCount++;
        }

        VerletObject chainEndObj = Solver.newObject(chainEnd,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        Solver.newLink(chainEndObj,Solver.objects[objCount-1],step);
    }

    private static void spoutWorld(){
        Solver.newConstraint(new CircularBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new Spout(250,50).setShootAcceleration(250_000,0));
        Solver.newGeneric(new Spout(250,60).setShootAcceleration(250_000,0));
    }
}
