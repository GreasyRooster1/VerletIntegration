package main.Worlds;


import main.Constraints.CircularBorderConstraint;
import main.Constraints.MouseConstraint;
import main.PhysicsObjects.Spout;
import main.Solver;
import main.World;

public class SpoutWorld extends World {

    @Override
    public void load() {
        Solver.newConstraint(new CircularBorderConstraint());
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new Spout(250,50).setShootAcceleration(250_000,0));
        Solver.newGeneric(new Spout(250,60).setShootAcceleration(250_000,0));
    }
}
