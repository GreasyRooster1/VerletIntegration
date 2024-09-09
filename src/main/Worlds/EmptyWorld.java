package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.Solver;
import main.World;

public class EmptyWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());
        Solver.newConstraint(new MouseConstraint());
    }
}
