package main;

public class CollisionThread implements Runnable{
    int sector;

    CollisionThread(int sector){
        this.sector = sector;
    }
    @Override
    public void run() {
        Solver.solveCollisionSector(sector,Solver.THREAD_POOL_SIZE);
    }
}
