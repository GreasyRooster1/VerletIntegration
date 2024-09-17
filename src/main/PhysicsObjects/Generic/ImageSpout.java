package main.PhysicsObjects.Generic;

import main.Main;
import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;
import main.Worlds.DeterministicWorld;
import processing.core.PImage;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;

import static java.lang.Math.sin;
import static main.Worlds.DeterministicWorld.image;
import static processing.core.PApplet.loadJSONArray;

public class ImageSpout extends PhysicsGeneric {
    public FastVec2 shootAcceleration;
    public double safeRadius = 10;
    public double radius = VerletObject.MAX_RADIUS;
    public double[] uData;
    public double[] vData;
    public double[] sizeData;

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

        VerletObject obj = Solver.newObject(position.x, position.y);
        int index = Solver.objects.length - 1;
        if(Solver.objects.length>=sizeData.length){
            obj.setColor(255,0,255);
        }else {
            //obj.setColor((float) (uData[index]*255), (float) (vData[index]*255),0);

            obj.setColor(image.get((int) (uData[index]*image.width), (int) (vData[index]*image.height)));
        }
        if(Solver.objects.length>=sizeData.length){
            obj.setRadius(Main.app.random(1)>0.5?4:5);
        }else{
            obj.setRadius(sizeData[index]);
        }
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

        uData = new double[values.size()];
        vData = new double[values.size()];
        sizeData = new double[values.size()];

        for (int i = 0; i < values.size(); i++) {

            JSONObject dataPoint = values.getJSONObject(i);

            double u = dataPoint.getDouble("u");
            double v = dataPoint.getDouble("v");
            int radius = dataPoint.getInt("radius");

            uData[i] = u;
            vData[i] = v;
            sizeData[i] = radius;
        }
        return this;
    }
}
