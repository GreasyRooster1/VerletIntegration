package main;

import main.PhysicsObjects.VerletObject;
import processing.core.PApplet;

public class Main extends PApplet {
    public static Main app;
    public static double dt = 1;

    public void settings(){
        size(500,500);
    }

    public void setup(){
        app = this;
        Solver.setup();
        World.create();
    }

    public void draw(){
        dt = 1/frameRate;

        background(0);
        Input.getInput();
        Solver.update(dt);
        Renderer.render();
        drawHUD();
    }

    public void drawHUD(){
        fill(255);
        text("FPS:"+frameRate,10,10);
        text("Obj Count:"+Solver.objects.length,10,20);
        text("∆t:"+dt,10,30);
    }


    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "main.Main" },  new Main());
    }

    public void ellipse(double x,double y,double r){
        ellipse((float) x, (float) y,(float) r*2,(float) r*2);
    }
    public void line(double x1,double y1,double x2,double y2){
        line((float) x1,(float) y1,(float) x2,(float) y2);
    }
    public void rect(double x1,double y1,double x2,double y2){
        rect((float) x1,(float) y1,(float) x2,(float) y2);
    }
}