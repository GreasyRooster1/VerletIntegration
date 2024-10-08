package main.Worlds;


import main.Constraints.CircleConstraint;
import main.Constraints.MouseConstraint;
import main.Constraints.WindowBorderConstraint;
import main.Main;
import main.PhysicsObjects.Generic.ImageSpout;
import main.PhysicsObjects.Generic.NewtonianGravity;
import main.PhysicsObjects.Generic.Spout;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;
import main.World;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

import static java.lang.Math.sin;


public class DeterministicWorld extends World {
    public static PImage image;

    @Override
    public void load() {
        image = Main.app.loadImage("resources/dwil.png");

        Solver.constantDeltaTime = true;

        Solver.newConstraint(new WindowBorderConstraint());

        Solver.newConstraint(new CircleConstraint(0,Main.app.height,25));
        Solver.newConstraint(new CircleConstraint(Main.app.width,Main.app.height,25));
        Solver.newConstraint(new CircleConstraint(Main.app.width/2,Main.app.height/2,5));

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
