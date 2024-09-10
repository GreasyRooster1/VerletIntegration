package main.Util;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class FastVec2 {
    public static final FastVec2 ZERO = new FastVec2(0, 0);
    public final double x,y;

    public FastVec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public FastVec2 add(FastVec2 b) {
        return new FastVec2(x + b.x, y + b.y);
    }

    public FastVec2 sub(FastVec2 b) {
        return new FastVec2(x - b.x, y - b.y);
    }

    public FastVec2 scalarMult(double b) {
        return new FastVec2(x*b,y*b);
    }

    public FastVec2 scalarDiv(double b) {
        return new FastVec2(x/b,y/b);
    }

    public FastVec2 normalized(){
        double s = getLength();
        if (s == 0.0) {
            return new FastVec2(0,0);
        } else {
            return scalarMult(1.0 / s);
        }
    }

    public double getSqrLength() {
        return x*x + y*y;
    }

    public double getLength() {
        return Math.sqrt(x*x + y*y);
    }

    public FastVec2 negate(){
        return new FastVec2(-x,-y);
    }

    public FastVec2 clone(){
        return new FastVec2(x,y);
    }
}
