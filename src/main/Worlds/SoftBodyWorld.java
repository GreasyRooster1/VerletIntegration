package main.Worlds;

import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.SoftBody;
import main.Solver;
import main.World;

public class SoftBodyWorld extends World {
    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new SoftBody(250,250,200,20));
    }
}
