package main.Worlds;

import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.Generic.NewtonianGravity;
import main.PhysicsObjects.Generic.SoftBody;
import main.Solver;
import main.Util.FastVec2;
import main.World;

public class GravityWorld extends World {
    @Override
    public void load() {
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new NewtonianGravity(250,250));

        Solver.gravity = FastVec2.ZERO;
    }
}
