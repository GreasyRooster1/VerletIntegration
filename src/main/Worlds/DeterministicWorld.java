package main.Worlds;


import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.Main;
import main.PhysicsObjects.Generic.ImageSpout;
import main.PhysicsObjects.Generic.Spout;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.World;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;


public class DeterministicWorld extends World {
    public static PImage image;

    @Override
    public void load() {
        image = Main.app.loadImage("resources/image.jpg");

        Solver.newConstraint(new WindowBorderConstraint());

        Solver.newGeneric(new ImageSpout(15,15).setShootAcceleration(700_000,0).setData("resources/data.json"));
    }

    public static void saveJSON(){
        JSONArray values = new JSONArray();

        for (int i = 0; i < Solver.objects.length; i++) {
            JSONObject jsonObject = new JSONObject();
            VerletObject object = Solver.objects[i];

            int x = (int) ((object.positionCurrent.x/Main.app.width)*image.width);
            int y = (int) ((object.positionCurrent.y/Main.app.height)*image.height);

            int color = image.get(x,y);

            jsonObject.setInt("color",color);

            values.setJSONObject(i, jsonObject);
        }

        Main.app.saveJSONArray(values, "resources/data.json");
    }
}
