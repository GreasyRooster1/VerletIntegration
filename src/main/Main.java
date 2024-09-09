package main;

import main.Rendering.Color;
import main.Rendering.Renderer;
import processing.core.PApplet;
import processing.event.MouseEvent;

public class Main extends PApplet {
    public static Main app;
    public static double dt = 1;
    public static double timeWarp = 1;
    public static int maxObjects = 0;

    public void settings(){
        size(500,500);
    }

    public void setup(){
        app = this;

        frameRate(120);
        Solver.setup();
        WorldManager.loadWorld();
    }

    public void draw(){
        dt = (1/frameRate)* timeWarp;

        background(0);
        Input.getInput();
        Solver.update(dt);
        Renderer.render();

        HUD.update();
    }

    public void mouseWheel(MouseEvent event) {
        float e = event.getCount();
        timeWarp+=e/-10;
        if(timeWarp<0){
            timeWarp=0;
        }
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