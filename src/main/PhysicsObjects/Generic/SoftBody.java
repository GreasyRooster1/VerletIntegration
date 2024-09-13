package main.PhysicsObjects.Generic;


import main.Main;
import main.PhysicsObjects.PhysicsGeneric;
import main.PhysicsObjects.VerletObject;
import main.Solver;
import main.Util.FastVec2;

import static java.lang.Math.*;
import static processing.core.PApplet.append;
import static processing.core.PApplet.println;

public class SoftBody extends PhysicsGeneric {
    // 1.0 moles of substance, at 293.15 kelvin (room temperature, 20°C)
    public static double NRT = 1.0 * 8.3144621 * 293.15;

    public int[] objectIds = {};

    public FastVec2 center = FastVec2.ZERO;

    SoftBody(double x, double y) {
        super(x, y);
        init(20,20);
    }

    public SoftBody(double x, double y, double radius, double segments) {
        super(x, y);
        init(radius,segments);
    }

    public void init(double radius,double segments){
        for(int i=0;i<segments;i++){
            double angle = (i/segments)*PI*2;
            Solver.newObject(position.x+cos(angle)*radius, position.y+sin(angle)*radius).setRadius(4);

            objectIds = append(objectIds, Solver.objects.length-1);
        }
        for(int i=0;i<objectIds.length;i++) {
            VerletObject obj1 = Solver.objects[objectIds[i]];
            int nextI = i+1>=objectIds.length?0:i+1;
            VerletObject obj2 = Solver.objects[nextI];
            Solver.newSpring(obj1,obj2,(radius*2*PI)/segments,3000);
        }
    }

    @Override
    public void update(double dt) {
        getCenter();
        for(int i=0;i<objectIds.length;i++){
            int nextI = i+1>=objectIds.length?0:i+1;
            VerletObject obj1 = Solver.objects[objectIds[i]];
            VerletObject obj2 = Solver.objects[objectIds[nextI]];

            FastVec2 axis = center.sub(obj1.positionCurrent);
            double length = axis.getLength();

            double pressure = (NRT / getArea())*200000;
            FastVec2 normalizedForceVector = axis.normalized();

            FastVec2 force = normalizedForceVector.scalarMult(pressure);

            obj1.applyForce(force);

//            Main.app.stroke(255);
//            Main.app.line(obj1.positionCurrent.x,obj1.positionCurrent.y,obj2.positionCurrent.x,obj2.positionCurrent.y);
        }
    }

    public void getCenter(){
        double x = 0;
        double y = 0;
        for(int i=0;i<objectIds.length;i++){
            VerletObject obj = Solver.objects[objectIds[i]];
            x+=obj.positionCurrent.x;
            y+=obj.positionCurrent.y;
        }
        x/=objectIds.length;
        y/=objectIds.length;
        center = new FastVec2(x, y);
    }

    public double getArea(){
        double area = 0;
        for(int i=0; i<objectIds.length;i++) {
            int nextI = i+1>=objectIds.length-1?0:i+1;

            VerletObject obj1 = Solver.objects[objectIds[i]];
            VerletObject obj2 = Solver.objects[objectIds[nextI]];

            area += obj1.positionCurrent.y *
                    obj2.positionCurrent.x -
                    obj1.positionCurrent.x *
                            obj2.positionCurrent.y;
        }
        return area * 0.5;
    }
}
