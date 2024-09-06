package main.PhysicsObjects;


import main.Solver;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import static java.lang.Math.*;
import static processing.core.PApplet.append;
import static processing.core.PApplet.println;

public class SoftBody extends PhysicsGeneric{
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
            Solver.newObject(position.getX()+cos(angle)*radius, position.getY()+sin(angle)*radius);
            objectIds = append(objectIds, Solver.objects.length);
        }
    }

    @Override
    public void update(double dt) {
        for(int i=0;i<objectIds.length-1;i++){
            VerletObject obj1 = Solver.objects[objectIds[i]];
            VerletObject obj2 = Solver.objects[objectIds[i<objectIds.length-2?i+1:0]];

            Vector2D axis = obj1.positionCurrent.subtract(obj2.positionCurrent);
            double length = axis.getNorm();

            double forceAmount = (length*NRT) / getArea() * 1000;
            Vector2D normalizedForceVector = axis.normalize();

            obj1.applyForce(normalizedForceVector.scalarMultiply(forceAmount));
            obj2.applyForce(normalizedForceVector.scalarMultiply(-forceAmount));
        }
    }

    public double getArea(){
        double area = 0;
        for(int i=0; i<objectIds.length-1;i++) {
            int nextI = i+1>=objectIds.length-1?0:i+1;

            VerletObject obj1 = Solver.objects[objectIds[i]];
            VerletObject obj2 = Solver.objects[objectIds[nextI]];

            area += obj1.positionCurrent.getY() *
                    obj2.positionCurrent.getX() -
                    obj1.positionCurrent.getX() *
                            obj2.positionCurrent.getY();
        }
        return area * 0.5;
    }
}
