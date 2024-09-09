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

        updateMaxObjects();
        drawHUD();
    }

    public void mouseWheel(MouseEvent event) {
        float e = event.getCount();
        timeWarp+=e/-10;
        if(timeWarp<0){
            timeWarp=0;
        }
    }

    public void updateMaxObjects(){
        if(frameRate>=60){
            maxObjects=Solver.objects.length;
        }
    }

    public void drawHUD(){
        fill(255);
        textAlign(LEFT);
        text("FPS:"+frameRate,3,10);
        text("Obj Count:"+Solver.objects.length,3,20);
        text("∆t:"+dt,3,30);
        text("timeWarp:"+timeWarp,3,40);
        text("Max Obj Count:"+maxObjects,3,50);

        textAlign(RIGHT);
        text("World: "+WorldManager.getCurrentWorld().getClass().getName(),497,10);
        text("Color Mode: "+ Renderer.colorMode.name(),497,20);
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