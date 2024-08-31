package main;

import main.Constraints.CircularBorderConstraint;

public class Renderer {
    public static void render(){
        renderConstraints();
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
}
