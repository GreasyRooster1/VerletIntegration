package main;

public class Input {
    public static void getInput(){
        if(Main.app.mousePressed){
            Solver.newObject(Main.app.mouseX,Main.app.mouseY);
        }
    }
}
