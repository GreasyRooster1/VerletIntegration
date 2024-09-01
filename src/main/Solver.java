package main;

import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static java.lang.Math.floor;
import static processing.core.PApplet.*;

public class Solver {
    public static VerletObject[] objects = {};
    public static Link[] links = {};
    public static Constraint[] constraints = {};

    public static CollisionCell[][] collisionCells = {};

    public static int subSteps = 8;

    public static Vector2D gravity = new Vector2D(0, 1000);

    public static VerletObject newObject(double x, double y){
        VerletObject o = new VerletObject(x,y);
        objects = (VerletObject[]) append(objects,o);
        return o;
    }
    public static Link newLink(VerletObject obj1,VerletObject obj2,double targetDist){
        Link l = new Link(obj1,obj2,targetDist);
        links = (Link[]) append(links,l);
        return l;
    }
    public static void newConstraint(Constraint c){
        constraints = (Constraint[]) append(constraints,c);
    }

    public static void setup(){
        setupConstraints();
        setupCollisionCells();
    }

    static void setupCollisionCells(){
        double stepSize = VerletObject.DEFAULT_RADIUS*2;
        for (int i = 0; i < Main.app.width/stepSize; i++) {
            CollisionCell[] row = {};
            for (int j = 0; j < Main.app.height/stepSize; j++) {
                row = (CollisionCell[]) append(row,new CollisionCell(i,j));
            }
            collisionCells = (CollisionCell[][]) append(collisionCells,row);
        }
    }

    static void setupConstraints(){
        newConstraint(new WindowBorderConstraint());
        newConstraint(new MouseConstraint());
    }

    static void update(double dt){
        double subDt = dt/subSteps;
        for(int i = 0; i < subSteps; i++) {
            applyGravity();
            applyConstraint();
            applyLinks();
            enterCollisionCells();
            solveCollisions();
            updatePositions(subDt);
        }
    }

    static void updatePositions(double dt){
        for (VerletObject obj:objects){
            obj.updatePosition(dt);
        }
    }

    static void applyGravity(){
        for (VerletObject obj:objects){
            obj.accelerate(gravity);
        }
    }

    public static void applyLinks(){
        for (Link link:links){
            link.apply();
        }
    }

    public static void applyConstraint(){
        for (VerletObject obj:objects){
            for(Constraint c:constraints){
                c.apply(obj);
            }
        }
    }

    public static void solveCollisions(){
        for (VerletObject obj1:objects) {
            for (VerletObject obj2 : objects) {
                if(obj1.ID.equals(obj2.ID)){
                    continue;
                }
                solveCollisionBetweenObjects(obj1,obj2);
            }
        }
    }

    public static void solveCollisionBetweenObjects(VerletObject obj1, VerletObject obj2){
        Vector2D collisionAxis = obj1.positionCurrent.subtract(obj2.positionCurrent);
        double dist = collisionAxis.getNorm();
        if(dist < obj1.radius+obj2.radius){
            Vector2D n =  new Vector2D(collisionAxis.getX()/dist, collisionAxis.getY()/dist);
            double delta = obj1.radius+obj2.radius - dist;
            obj1.positionCurrent = obj1.positionCurrent.add(n.scalarMultiply(delta).scalarMultiply(.5));
            obj2.positionCurrent = obj2.positionCurrent.subtract(n.scalarMultiply(delta).scalarMultiply(.5));

        }
    }

    public static void resetCollisionCells(){
        for (CollisionCell[] row:Solver.collisionCells){
            for (CollisionCell cell:row) {
                cell.objectIndices = new int[]{};
            }
        }
    }

    public static void enterCollisionCells(){
        resetCollisionCells();
        int count = 0;
        for (VerletObject obj:objects) {
            int i = (int) floor(obj.positionCurrent.getX()/(VerletObject.DEFAULT_RADIUS*2));
            int j = (int) floor(obj.positionCurrent.getY()/(VerletObject.DEFAULT_RADIUS*2));

            i = max(0,min((int) floor(500/(VerletObject.DEFAULT_RADIUS*2)),i));
            j = max(0,min((int) floor(500/(VerletObject.DEFAULT_RADIUS*2)),j));

            CollisionCell cell = collisionCells[i][j];
            cell.objectIndices = append(cell.objectIndices,count);
            count++;
        }
    }
}
