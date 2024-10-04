package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.World;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class BoxWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int width = 5;
        int height = 5;
        double stepSize = VerletObject.MAX_RADIUS *2;
        double diagonalStepSize = sqrt(pow(stepSize,2)*2);
        double xPos = 250-stepSize/2*width;
        double yPos = 250-stepSize/2*height;

        int objCount = 0;

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                Solver.newObject(xPos+(x*stepSize),yPos+(y*stepSize)).setColor(255,255,255).setRadius(VerletObject.MAX_RADIUS);
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
}
