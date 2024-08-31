public class Renderer {
    public static void render(){
        for (VerletObject obj:Solver.objects){
            Main.app.fill(255);
            Main.app.noStroke();
            Main.app.ellipse(obj.positionCurrent.getX(),obj.positionCurrent.getY(),obj.radius);
        }
    }
}
