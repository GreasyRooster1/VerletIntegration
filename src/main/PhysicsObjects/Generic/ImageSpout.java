package main.PhysicsObjects.Generic;

import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;

import static processing.core.PApplet.loadJSONArray;

public class ImageSpout extends PhysicsGeneric {
    public FastVec2 shootAcceleration;
    public double safeRadius = 10;
    public double radius = VerletObject.MAX_RADIUS;
    public int[] colorData;

    public ImageSpout(double x, double y) {
        super(x,y);
    }

    public void update(double dt){
        for(VerletObject obj: Solver.objects){
            FastVec2 collisionAxis = obj.positionCurrent.sub(position);
            double dist = collisionAxis.getLength();
            if(dist < safeRadius+obj.radius){
                return;
            }
        }
        VerletObject obj = Solver.newObject(position.x, position.y).setRadius(radius);
        obj.setAcceleration(shootAcceleration);
    }

    public ImageSpout setShootAcceleration(double x, double y) {
        shootAcceleration = new FastVec2(x, y);
        return this;
    }

    public ImageSpout setSafeRadius(double r) {
        safeRadius = r;
        return this;
    }

    public ImageSpout setRadius(double r) {
        radius = r;
        return this;
    }

    public ImageSpout setData(String path){
        JSONArray values = loadJSONArray(new File(path));

        colorData = new int[values.size()];

        for (int i = 0; i < values.size(); i++) {

            JSONObject dataPoint = values.getJSONObject(i);

            int color = dataPoint.getInt("color");

            colorData[i] = color;
        }
        return this;
    }
}
