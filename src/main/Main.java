package main;

import processing.core.PApplet;

public class Main extends PApplet {
    public static Main app;
    public static double dt = 1;

    public void settings(){
        size(500,500);
    }

    public void setup(){
        app = this;
        Solver.setupConstraints();
        createMap();
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

    public void createMap(){
        Solver.newObject(50,350).setStatic(true).setRadius(15).setColor(255,255,255);
        Solver.newObject(450,350).setStatic(true).setRadius(15).setColor(255,255,255);

        int total = 50;
        float step = 450f/total;
        for (float i = 50+step; i < 450f; i+=step) {
            Solver.newObject(i,350).setRadius(step/2).setColor(255,255,255);
        }
    }

    public void ellipse(double x,double y,double r){
        ellipse((float) x, (float) y,(float) r*2,(float) r*2);
    }
    public void line(double x1,double y1,double x2,double y2){
        line((float) x1,(float) y1,(float) x2,(float) y2);
    }
}