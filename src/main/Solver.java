package main;

import main.PhysicsObjects.Link;
import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.Generic.Spring;
import main.PhysicsObjects.VerletObject;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static java.lang.Math.floor;
import static processing.core.PApplet.*;

public class Solver {
    public static VerletObject[] objects = {};
    public static Link[] links = {};
    public static Spring[] springs = {};
    public static Constraint[] constraints = {};
    public static PhysicsGeneric[] generics = {};

    public static CollisionCell[][] collisionCells = {};

    public static int subSteps = 8;

    public static final Vector2D DEFAULT_GRAVITY = new Vector2D(0, 1000);
    public static Vector2D gravity = DEFAULT_GRAVITY;

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
    public static Spring newSpring(VerletObject obj1,VerletObject obj2,double restLength,double stiffness){
        Spring l = new Spring(obj1,obj2,restLength,stiffness);
        springs = (Spring[]) append(springs,l);
        return l;
    }
    public static void newConstraint(Constraint c){
        constraints = (Constraint[]) append(constraints,c);
    }
    public static PhysicsGeneric newGeneric(PhysicsGeneric g){
        generics = (PhysicsGeneric[]) append(generics,g);
        return g;
    }

    public static void setup(){
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

    static void update(double dt){
        double subDt = dt/subSteps;
        for(int i = 0; i < subSteps; i++) {
            updateGenerics(dt);

            applyGravity();

            applyConstraint();
            applyLinks();
            applySprings();

            enterCollisionCells();
            solveCollisions();

            updatePositions(subDt);
        }
    }

    static void updateGenerics(double dt){
        for(PhysicsGeneric generic:generics){
            generic.update(dt);
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

    public static void applySprings(){
        for (Spring spring:springs){
            spring.apply();
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
        for(int x = 0; x < collisionCells.length; x++){
            for(int y = 0; y < collisionCells[x].length; y++){
                CollisionCell currentCell = collisionCells[x][y];
                for(int dx=-1; dx <= 1; dx++){
                    for(int dy=-1; dy <= 1; dy++){
                        int getX = x+dx;
                        int getY = y+dy;
                        if(getX<0||getX>=collisionCells.length||getY<0||getY>=collisionCells[x].length){
                            continue;
                        }
                        CollisionCell otherCell =collisionCells[getX][getY];
                        solveCollisionBetweenCells(currentCell,otherCell);
                    }
                }
            }
        }
    }

    public static void solveCollisionBetweenCells(CollisionCell cell1,CollisionCell cell2){
        for(int obj1Index:cell1.objectIndices){
            for(int obj2Index:cell2.objectIndices){
                if(obj1Index==obj2Index){
                    continue;
                }
                solveCollisionBetweenObjects(objects[obj1Index], objects[obj2Index]);
            }
        }
    }

    public static void solveCollisionBetweenObjects(VerletObject obj1, VerletObject obj2){
        Vector2D collisionAxis = obj1.positionCurrent.subtract(obj2.positionCurrent);
        double dist = collisionAxis.getNorm();
        if(dist < obj1.radius+obj2.radius){
            Vector2D n =  new Vector2D(collisionAxis.getX()/dist, collisionAxis.getY()/dist);
            double delta = obj1.radius+obj2.radius - dist;
            Vector2D diff = n.scalarMultiply(delta).scalarMultiply(.5);
            obj1.positionCurrent = obj1.positionCurrent.add(diff);
            obj2.positionCurrent = obj2.positionCurrent.subtract(diff);

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
            int i = (int) floor(obj.positionCurrent.getX()/CollisionCell.CELL_SIZE);
            int j = (int) floor(obj.positionCurrent.getY()/CollisionCell.CELL_SIZE);

            i = max(0,min((int) floor(Main.app.width/CollisionCell.CELL_SIZE)-1,i));
            j = max(0,min((int) floor(Main.app.height/CollisionCell.CELL_SIZE)-1,j));

            CollisionCell cell = collisionCells[i][j];
            cell.objectIndices = append(cell.objectIndices,count);
            count++;
        }
    }
}
