import static processing.core.PApplet.sin;
import static processing.core.PConstants.PI;

public class Color {
    public static int getRainbow(float t){
        float r = sin(t);
        float g = sin(t + 0.33f * 2.0f * PI);
        float b = sin(t + 0.66f * 2.0f * PI);
        return Main.app.color(r*r*255, g*g*255, b*b*255);
    }
}
