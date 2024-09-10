package main;

import java.util.concurrent.Callable;

public class CollisionThread implements Callable<Integer> {
    int sector;

    CollisionThread(int sector){
        this.sector = sector;
    }

    @Override
    public Integer call() throws Exception {
        Solver.solveCollisionSector(sector,Solver.THREAD_POOL_SIZE);
        return 0;
    }
}
