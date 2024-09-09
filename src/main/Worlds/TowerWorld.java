package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.Main;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.World;

public class TowerWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        int stackSize = 20;
        double size = VerletObject.MAX_RADIUS;
        double ratio = (double) (Main.app.height-100) /stackSize;
        for(int i=1;i<stackSize;i++){
            Solver.newObject(250,500-i*ratio).setRadius(size);
        }
    }
}
