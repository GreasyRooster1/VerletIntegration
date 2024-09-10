package main.PhysicsObjects;

import main.Rendering.Color;
import main.Main;
import main.Util.FastVec2;

import java.util.UUID;

import static processing.core.PApplet.println;

public class VerletObject {
    public final UUID ID;

    public final FastVec2 initalPosition;
    public FastVec2 positionCurrent;
    public FastVec2 positionOld;
    public FastVec2 acceleration = FastVec2.ZERO;
    public boolean isStatic = false;

    public int color;

    public double radius;
    public static final double MAX_RADIUS =7;

    public double mass = 1;

    public VerletObject(double x, double y) {
        positionCurrent = new FastVec2(x, y);
        positionOld = new FastVec2(x, y);
        initalPosition = new FastVec2(x, y);

        color = Color.getRainbow(Main.app.frameCount/100f);

        radius = Main.app.random(2f, (float) MAX_RADIUS);

        ID = UUID.randomUUID();
    }

    public void updatePosition(double dt){
        if(isStatic){
            acceleration = FastVec2.ZERO;
            positionCurrent = initalPosition;
            return;
        }
        FastVec2 velocity = getVelocity();

        positionOld = positionCurrent.clone();
        positionCurrent = positionCurrent.add(velocity.add(acceleration.scalarMult(dt*dt)));

        //positionCurrent = new FastVec2(positionCurrent.getX()%Main.app.width,positionCurrent.getY()%Main.app.height);

        acceleration = new FastVec2(0,0);
    }

    public FastVec2 getVelocity(){
        return positionCurrent.sub(positionOld);
    }

    public void accelerate(FastVec2 acc) {
        acceleration = acceleration.add(acc);
    }
    public void applyForce(FastVec2 force) {
        acceleration = acceleration.add(force.scalarMult(1/mass));
    }

    public VerletObject setStatic(boolean isStatic) {
        this.isStatic = isStatic;
        return this;
    }
    public VerletObject setRadius(double radius) {
        this.radius = radius;
        return this;
    }
    public VerletObject setColor(float r, float g, float b) {
        this.color = Main.app.color(r,g,b);
        return this;
    }

    public VerletObject setAcceleration(FastVec2 FastVec2) {
        acceleration = FastVec2;
        return this;
    }
}
