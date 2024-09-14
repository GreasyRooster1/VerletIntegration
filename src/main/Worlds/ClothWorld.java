package main.Worlds;

import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.World;

public class ClothWorld extends World {
    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int width = 20;
        int height = 20;
        double stepSize = 12;
        double xPos = 250-(stepSize*height/2);
        double yPos = 50;

        double stiffness = 10000;


        int objCount = 0;

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                VerletObject obj = Solver.newObject(xPos+(x*stepSize),yPos+(y*stepSize)).setColor(255,255,255).setRadius(2);

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
}
