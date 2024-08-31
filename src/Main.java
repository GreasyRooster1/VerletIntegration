import processing.core.PApplet;

import javax.swing.*;

public class Main extends PApplet {
    public Main app;

    public void settings(){
        size(500,500);
    }

    public void setup(){
        app = this;
        Solver.objects = (VerletObject[]) append(Solver.objects,new VerletObject(250,250));
    }

    public void draw(){
        background(0);
        Solver.update(0.01);
    }


    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Main" },  new Main());
    }
}