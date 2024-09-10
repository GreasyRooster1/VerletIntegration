package main.PhysicsObjects.Generic;


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
            Solver.newObject(position.x+cos(angle)*radius, position.y+sin(angle)*radius);
            objectIds = append(objectIds, Solver.objects.length-1);
        }
    }

    @Override
    public void update(double dt) {
        for(int i=0;i<objectIds.length;i++){
            int nextI = i+1>=objectIds.length-1?0:i+1;
            VerletObject obj1 = Solver.objects[objectIds[i]];
            VerletObject obj2 = Solver.objects[objectIds[nextI]];

            FastVec2 axis = obj1.positionCurrent.sub(obj2.positionCurrent);
            double length = axis.getLength();

            double forceAmount = (length*NRT) / getArea() * 1000;
            FastVec2 normalizedForceVector = axis.normalized();

            obj1.applyForce(normalizedForceVector.scalarMult(forceAmount));
            obj2.applyForce(normalizedForceVector.scalarMult(-forceAmount));
        }
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
