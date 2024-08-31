public class Renderer {
    public static void render(){
        renderConstraint();
        renderVerletObjects();
    }

    public static void renderVerletObjects(){
        for (VerletObject obj:Solver.objects){
            Main.app.fill(255);
            Main.app.noStroke();
            Main.app.ellipse(obj.positionCurrent.getX(),obj.positionCurrent.getY(),obj.radius);
        }
    }

    public static void renderConstraint(){
        Main.app.fill(127);
        Main.app.ellipse(BorderConstraint.center.getX(),BorderConstraint.center.getY(),BorderConstraint.radius);
    }
}
