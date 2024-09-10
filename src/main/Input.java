package main;

import main.PhysicsObjects.VerletObject;
import main.Rendering.ColorMode;
import main.Rendering.Renderer;
import main.Util.FastVec2;

public class Input {
    public static boolean mousePrevPressed = false;
    public static boolean keyPrevPressed = false;
    public static FastVec2 startLoc = FastVec2.ZERO;
    public static double safeRadius = 10;


    public static void getInput(){
        getMouse();
        getKeyboard();
        doGravity();
    }

    public static void doGravity(){
        if(Main.app.keyPressed) {
            if (Main.app.key == 'g') {
                Solver.gravity = new FastVec2(Main.app.mouseX-250, Main.app.mouseY-250).scalarMult(8);
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
            if(Main.app.key=='d'){
                HUD.debugActive = !HUD.debugActive;
            }
        }

        keyPrevPressed = Main.app.keyPressed;
    }

    public static void getMouse(){
        if(Main.app.mousePressed&&!mousePrevPressed){
            startLoc = new FastVec2(Main.app.mouseX,Main.app.mouseY);
            mousePrevPressed = true;
            return;
        }

        if(Main.app.mousePressed){
            if(startLoc.x==Main.app.mouseX&&startLoc.y==Main.app.mouseY){
                return;
            }
            for(VerletObject obj:Solver.objects){
                FastVec2 collisionAxis = obj.positionCurrent.sub(startLoc);
                double dist = collisionAxis.getLength();
                if(dist < safeRadius+obj.radius){
                    return;
                }
            }
            double x = Main.app.mouseX-startLoc.x;
            double y = Main.app.mouseY-startLoc.y;
            Solver.newObject(startLoc.x,startLoc.y).setAcceleration(new FastVec2(x,y).scalarMult(1500));
        }

        mousePrevPressed = Main.app.mousePressed;
    }
}
