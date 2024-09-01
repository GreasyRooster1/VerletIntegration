package main;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static processing.core.PApplet.*;

public class Input {
    public static boolean mousePrevPressed = false;
    public static Vector2D startLoc = Vector2D.ZERO;
    public static double safeRadius = 10;

    public static void getInput(){
        if(Main.app.mousePressed&&!mousePrevPressed){
            startLoc = new Vector2D(Main.app.mouseX,Main.app.mouseY);
            mousePrevPressed = true;
            return;
        }

        if(Main.app.mousePressed){
            if(startLoc.getX()==Main.app.mouseX&&startLoc.getY()==Main.app.mouseY){
                return;
            }
            for(VerletObject obj:Solver.objects){
                Vector2D collisionAxis = obj.positionCurrent.subtract(startLoc);
                double dist = collisionAxis.getNorm();
                if(dist < safeRadius+obj.radius){
                    return;
                }
            }
            double x = Main.app.mouseX-startLoc.getX();
            double y = Main.app.mouseY-startLoc.getY();
            Solver.newObject(startLoc.getX(),startLoc.getY()).setAcceleration(new Vector2D(x,y).scalarMultiply(1500));
        }

        mousePrevPressed = Main.app.mousePressed;
    }
}
