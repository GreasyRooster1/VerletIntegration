package main;

import main.Constraints.CircularBorderConstraint;

public class Renderer {
    public static void render(){
        renderConstraints();
        renderLinks();
        renderVerletObjects();
    }

    private static void renderConstraints() {
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
