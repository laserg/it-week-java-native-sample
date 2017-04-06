package com.epam.it_week.fractal.jnr;

import jnr.ffi.LibraryLoader;
import jnr.ffi.Runtime;
import jnr.ffi.annotations.Out;
import jnr.ffi.types.int32_t;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Fractal {
    private static LibC API;
    static {
        String fractalLib = "nativeBenchmark";
        String libPath = fractalLib +
                (System.getProperty("os.arch").equals("x86") ? "_x32" : "_x64");
            System.loadLibrary(libPath);
        API = LibraryLoader.create(LibC.class).load(libPath);
    }

    public interface LibC {
        void generate(@Out Point[] image, @int32_t int x, @int32_t int y);
    }

    private final Point[] pointMap;
    private final int width;
    private final int height;

    public Fractal(int w, int h) {
        width = w;
        height = h;
        Runtime runtime = Runtime.getRuntime(API);
        pointMap = Point.arrayOf(runtime, Point.class, w * h);
        API.generate(pointMap, w, h);
    }

    public void draw(String path) throws IOException {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (Point point : pointMap) {
            image.setRGB(point.x.get(), point.y.get(), point.getColor().getRGB());
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
