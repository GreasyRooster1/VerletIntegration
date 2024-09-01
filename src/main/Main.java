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
        int objCount = 1;
        double endRadius = VerletObject.DEFAULT_RADIUS;
        float step = (float) VerletObject.DEFAULT_RADIUS*2;
        float chainHeight = 250;

        Solver.newObject(50,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        for (float i = 50+step; i < 450f; i+=step) {
            Solver.newObject(i,chainHeight).setColor(255,255,255);
            if(objCount==1) {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step/2+endRadius);
            }else {
                Solver.newLink(Solver.objects[objCount - 1], Solver.objects[objCount], step);
            }
            objCount++;
        }

        VerletObject chainEnd = Solver.newObject(450,chainHeight).setStatic(true).setRadius(endRadius).setColor(255,255,255);

        Solver.newLink(chainEnd,Solver.objects[objCount-1],step);
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