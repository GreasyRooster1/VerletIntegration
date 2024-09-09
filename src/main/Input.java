package main;

import main.PhysicsObjects.VerletObject;
import main.Rendering.ColorMode;
import main.Rendering.Renderer;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

public class Input {
    public static boolean mousePrevPressed = false;
    public static boolean keyPrevPressed = false;
    public static Vector2D startLoc = Vector2D.ZERO;
    public static double safeRadius = 10;


    public static void getInput(){
        getMouse();
        getKeyboard();
        doGravity();
    }

    public static void doGravity(){
        if(Main.app.keyPressed) {
            if (Main.app.key == 'g') {
                Solver.gravity = new Vector2D(Main.app.mouseX-250, Main.app.mouseY-250).scalarMultiply(8);


            }
        }
    }

    public static void getKeyboard(){
        if(!keyPrevPressed&&Main.app.keyPressed){
            if(Main.app.key=='c'){
                int index = Renderer.colorMode.ordinal() >= ColorMode.values().length-1?0:Renderer.colorMode.ordinal()+1;
                Renderer.colorMode = ColorMode.values()[index];
            }
            if(Main.app.key=='w'){
                WorldManager.cycle();
            }
            if(Main.app.key=='r'){
                WorldManager.reload();
            }
        }

        keyPrevPressed = Main.app.keyPressed;
    }

    public static void getMouse(){
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
