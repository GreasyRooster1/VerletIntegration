package main;

public class Renderer {
    public static void render(){
        renderConstraints();
        renderLinks();
        renderVerletObjects();
        renderConstraints();
        renderCollisionCells();
    }

    public static void renderCollisionCells(){
        for (CollisionCell[] row:Solver.collisionCells){
            for (CollisionCell cell:row){
                Main.app.strokeWeight(1);
                Main.app.stroke(0,0,255,50);
                Main.app.fill(255,cell.objectIndices.length*50);
                Main.app.rect(cell.x*VerletObject.DEFAULT_RADIUS*2,cell.y*VerletObject.DEFAULT_RADIUS*2,VerletObject.DEFAULT_RADIUS*2,VerletObject.DEFAULT_RADIUS*2);
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
