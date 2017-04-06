package com.epam.it_week.fractal.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey Larin on 15.03.2017.
 */

public class Fractal {
    interface NativeAPI extends Library {
        void generate(Point.ByReference image, int w, int h);
    }
    private static NativeAPI API;
    static {
        String fractalLib = "nativeBenchmark";
        String libPath = fractalLib + (System.getProperty("os.arch").equals("x86") ? "_x32" : "_x64");
        System.loadLibrary(libPath);
        API = Native.loadLibrary(libPath, NativeAPI.class);
    }

    private final Point[] pointMap;
    private final int width;
    private final int height;

    public Fractal(int w, int h) {
        width = w;
        height = h;
        Point.ByReference pointMapRef = new Point.ByReference();
        pointMap = (Point[]) pointMapRef.toArray(w * h);
        API.generate(pointMapRef, w, h);
    }

    public void draw(String path) throws IOException {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (Point point : pointMap) {
            image.setRGB(point.x, point.y, point.getColor().getRGB());
        }
        ImageIO.write(image, "jpg", new File(path));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
