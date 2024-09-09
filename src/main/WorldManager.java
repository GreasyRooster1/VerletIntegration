package main;

import main.PhysicsObjects.*;
import main.PhysicsObjects.Generic.Spring;
import main.Worlds.*;

public class WorldManager {
    public static World[] worlds = {
            new EmptyWorld(),
            new SpoutWorld(),
            new ChainWorld(),
            new BoxWorld(),
            new ClothWorld(),
            new SoftBodyWorld(),
            new GravityWorld(),
            new TowerWorld(),
    };

    public static int currentWorldId =0;

    public static void loadWorld() {
        getCurrentWorld().load();
    }

    public static void reload(){
        Solver.objects = new VerletObject[]{};
        Solver.links = new Link[]{};
        Solver.springs = new Spring[]{};
        Solver.constraints = new Constraint[]{};
        Solver.generics = new PhysicsGeneric[]{};

        Solver.gravity = Solver.DEFAULT_GRAVITY;

        loadWorld();
    }

    public static int cycle(){
        currentWorldId++;
        if(currentWorldId >= worlds.length){
            currentWorldId = 0;
        }
        reload();
        return currentWorldId;
    }

    public static World getCurrentWorld(){
        return worlds[currentWorldId];
    }
}
