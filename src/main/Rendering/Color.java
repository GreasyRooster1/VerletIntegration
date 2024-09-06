package main.Rendering;

import main.Main;
import main.PhysicsObjects.VerletObject;

import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static processing.core.PApplet.sin;
import static processing.core.PConstants.PI;

public class Color {
    public static int getRainbow(float t){
        float r = sin(t);
        float g = sin(t + 0.33f * 2.0f * PI);
        float b = sin(t + 0.66f * 2.0f * PI);
        return Main.app.color(r*r*255, g*g*255, b*b*255);
    }

    public static int determineColor(VerletObject object, ColorMode mode){
        switch(mode){
            case OBJECT_COLOR -> {return object.color;}
            case SPEED -> {return getRainbow((float) object.getVelocity().getNorm());}
            case RGB_VELOCITY -> {return Main.app.color(abs((float )object.getVelocity().getX()*255),
                                                        abs((float )object.getVelocity().getY()*255)
                                                        ,127f);}

            case DIRECTION -> {return getRainbow((float) atan2(object.getVelocity().getY(),object.getVelocity().getX()));}
            default -> {return 255;}
        }
    }
}
