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

import static java.lang.Math.sin;


public class DeterministicWorld extends World {
    public static PImage image;

    @Override
    public void load() {
        image = Main.app.loadImage("resources/img.jpg");

        Solver.constantDeltaTime = true;

        Solver.newConstraint(new WindowBorderConstraint());

        Solver.newGeneric(new ImageSpout(250,-1).setShootAcceleration(1,0).setData("resources/data.json"));
    }

    public static void saveJSON(){
        JSONArray values = new JSONArray();

        for (int i = 0; i < Solver.objects.length; i++) {
            JSONObject jsonObject = new JSONObject();
            VerletObject object = Solver.objects[i];

            double u = ((object.positionCurrent.x/Main.app.width));
            double v = ((object.positionCurrent.y/Main.app.height));


            jsonObject.setDouble("u",u);
            jsonObject.setDouble("v",v);
            jsonObject.setDouble("radius",object.radius);

            values.setJSONObject(i, jsonObject);
        }

        Main.app.saveJSONArray(values, "resources/data.json");
    }
}
