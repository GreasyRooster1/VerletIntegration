package main.Worlds;

import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.PhysicsObjects.Generic.NewtonianGravity;
import main.PhysicsObjects.Generic.SoftBody;
import main.Solver;
import main.World;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class GravityWorld extends World {
    @Override
    public void load() {
        Solver.newConstraint(new MouseConstraint());

        Solver.newGeneric(new NewtonianGravity(250,250));

        Solver.gravity = Vector2D.ZERO;
    }
}
