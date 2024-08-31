import processing.core.PApplet;

import javax.swing.*;

public class Main extends PApplet {
    public static Main app;

    public void settings(){
        size(500,500);
    }

    public void setup(){
        app = this;
        Solver.objects = (VerletObject[]) append(Solver.objects,new VerletObject(75,250));
        Solver.objects = (VerletObject[]) append(Solver.objects,new VerletObject(350,350));
    }

    public void draw(){
        background(0);
        Solver.update(0.01);
        Renderer.render();
    }


    public static void main(String[] args) {
        PApplet.runSketch(new String[] { "Main" },  new Main());
    }

    public void ellipse(double x,double y,double r){
        ellipse((float) x, (float) y,(float) r*2,(float) r*2);
    }
}