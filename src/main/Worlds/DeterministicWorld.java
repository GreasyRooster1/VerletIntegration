package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.Generic.ImageSpout;
import main.PhysicsObjects.Generic.Spout;
import main.Solver;
import main.World;

public class DeterministicWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new WindowBorderConstraint());

        Solver.newGeneric(new ImageSpout(250,50).setShootAcceleration(250_000,0).setData(""));
    }
}
