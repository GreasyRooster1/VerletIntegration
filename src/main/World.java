package main;

import main.Constraints.CircularBorderConstraint;
import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.SoftBody;
import main.PhysicsObjects.Spout;
import main.PhysicsObjects.VerletObject;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class World {
    public static void create() {
        softBodyWorld();
    }

    private static void softBodyWorld(){
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new SoftBody(250,250,40,30));
    }

    private static void clothWorld(){
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int width = 10;
        int height = 10;
        double stepSize = VerletObject.DEFAULT_RADIUS*4;
        double xPos = 250-(stepSize*height/2);
        double yPos = 50;

        double stiffness = 4000;


        int objCount = 0;

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                VerletObject obj = Solver.newObject(xPos+(x*stepSize),yPos+(y*stepSize)).setColor(255,255,255);

                if(y==0&(x==0||x==width-1)){
                    obj.setStatic(true);
                }

                if(objCount!=0&&y!=0) {
                    Solver.newSpring(Solver.objects[objCount - 1], Solver.objects[objCount], stepSize,stiffness);
                }
                if(x!=0){
                    Solver.newSpring(Solver.objects[objCount], Solver.objects[objCount-(height)], stepSize,stiffness);
                }
                objCount++;
            }
        }
    }

    private static void boxWorld(){
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int width = 5;
        int height = 5;
        double xPos = 250;
        double yPos = 250;
        double stepSize = VerletObject.DEFAULT_RADIUS*5;
        double diagonalStepSize = sqrt(pow(stepSize,2)*2);

        int objCount = 0;

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                Solver.newObject(xPos+(x*stepSize),yPos+(y*stepSize)).setColor(255,255,255);
                if(objCount!=0&&y!=0) {
                    Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], stepSize);
                }
                if(x!=0){
                    Solver.newLink(Solver.objects[objCount], Solver.objects[objCount-(height)], stepSize);
                    if(y!=height-1) {
                        Solver.newLink(Solver.objects[objCount], Solver.objects[(objCount - (height)) + 1], diagonalStepSize);
                    }
                    if(y!=0) {
                        Solver.newLink(Solver.objects[objCount], Solver.objects[(objCount - (height)) - 1], diagonalStepSize);
                    }
                }
                objCount++;
            }
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
