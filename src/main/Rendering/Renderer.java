package main.Rendering;

import main.CollisionCell;
import main.Constraint;
import main.Main;
import main.PhysicsObjects.Link;
import main.PhysicsObjects.Spring;
import main.PhysicsObjects.VerletObject;
import main.Solver;

import static java.lang.Math.abs;

public class Renderer {
    public static void render(){
        renderConstraints();
        renderLinks();
        renderSprings();
        renderVerletObjects();
        renderConstraints();
        //renderCollisionCells();
    }

    public static void renderSprings(){
        for (Spring spring: Solver.springs){
            float length = (float) spring.obj1.positionCurrent.subtract(spring.obj2.positionCurrent).getNorm();
            Main.app.strokeWeight(length/40f);
            Main.app.stroke(255);
            Main.app.line(spring.obj1.positionCurrent.getX(),spring.obj1.positionCurrent.getY(),spring.obj2.positionCurrent.getX(),spring.obj2.positionCurrent.getY());
        }
    }

    public static void renderCollisionCells(){
        for (CollisionCell[] row:Solver.collisionCells){
            for (CollisionCell cell:row){
                Main.app.strokeWeight(1);
                Main.app.stroke(0,0,255,50);
                Main.app.fill(255,cell.objectIndices.length*50);
                Main.app.rect(cell.x*CollisionCell.CELL_SIZE,cell.y*CollisionCell.CELL_SIZE,CollisionCell.CELL_SIZE,CollisionCell.CELL_SIZE);
            }
        }
    }

    public static void renderConstraints() {
        for(Constraint c:Solver.constraints){
            c.render();
        }
    }

    public static void renderVerletObjects(){
        for (VerletObject obj:Solver.objects){
            Main.app.fill(obj.color);
            Main.app.noStroke();
            Main.app.ellipse(obj.positionCurrent.getX(),obj.positionCurrent.getY(),obj.radius);
        }
    }

    public static void renderLinks(){
        for (Link link:Solver.links){
            Main.app.strokeWeight(3);
            Main.app.stroke(255);
            Main.app.line(link.obj1.positionCurrent.getX(),link.obj1.positionCurrent.getY(),link.obj2.positionCurrent.getX(),link.obj2.positionCurrent.getY());
        }
    }
}
