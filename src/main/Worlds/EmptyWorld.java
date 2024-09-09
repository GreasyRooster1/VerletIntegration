package main.Worlds;


import main.Constraints.CircularBorderConstraint;
import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.Spout;
import main.Solver;
import main.World;

public class EmptyWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());
    }
}
