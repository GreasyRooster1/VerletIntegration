package main;

public class Input {
    public static void getInput(){
        if(Main.app.mousePressed){
            VerletObject obj1 = Solver.newObject(Main.app.mouseX,Main.app.mouseY);
            VerletObject obj2 = Solver.newObject(Main.app.mouseX,Main.app.mouseY+40);
            Solver.newLink(obj1,obj2,40);
        }
    }
}
