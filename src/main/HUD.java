package main;

import main.Rendering.Renderer;

import static main.Main.*;
import static processing.core.PConstants.LEFT;

public class HUD {
    public static void update() {
        render();
        updateMaxObjects();
    }

    public static void updateMaxObjects(){
        if(app.frameRate>=60){
            maxObjects=Solver.objects.length;
        }
    }

    public static void render(){
        renderText();
        renderGravity();
    }

    public static void renderGravity(){
        if (!(Main.app.keyPressed&&Main.app.key=='g')) {
            return;
        }

        app.noFill();
        app.stroke(255);
        app.ellipse(250,250,75,75);

        app.line(250,250,250+Solver.gravity.getX(),250+Solver.gravity.getY());
    }

    public static void renderText(){
        app.fill(255);
        app.textAlign(LEFT);
        app.text("FPS:"+app.frameRate,3,10);
        app.text("Obj Count:"+ Solver.objects.length,3,20);
        app.text("∆t:"+dt,3,30);
        app.text("timeWarp:"+timeWarp,3,40);
        app.text("Max Obj Count:"+maxObjects,3,50);

        app.textAlign(RIGHT);
        app.text("World: "+ WorldManager.getCurrentWorld().getClass().getName(),497,10);
        app.text("Color Mode: "+ Renderer.colorMode.name(),497,20);
    }
}
