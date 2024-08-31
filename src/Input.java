public class Input {
    public static void getInput(){
        if(Main.app.mousePressed&Main.app.frameCount%10==0){
            Solver.newObject(Main.app.mouseX,Main.app.mouseY);
        }
    }
}
