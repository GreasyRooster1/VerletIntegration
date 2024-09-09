package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.Main;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.World;

public class ChainWorld extends World {

    @Override
    public void load() {
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
            Solver.newObject(i,chainHeight).setColor(255,255,255).setRadius(VerletObject.DEFAULT_RADIUS);
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
}
