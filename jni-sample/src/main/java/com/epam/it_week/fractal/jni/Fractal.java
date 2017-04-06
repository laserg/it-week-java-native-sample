package com.epam.it_week.fractal.jni;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Fractal {
    static {
        final String fractalLib = "nativeBenchmark";
        String pathLib = fractalLib + (System.getProperty("os.arch").equals("x86") ? "_x32" : "_x64");
        System.loadLibrary(pathLib);
    }

    private final Point[] pointMap;
    private final int width;
    private final int height;

    public Fractal(int w, int h) {
        width = w;
        height = h;
        pointMap = generate(w, h);
    }

    public void draw(String path) throws IOException {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        for (Point point : pointMap) {
            image.setRGB(point.getX(), point.getY(), point.getColor().getRGB());
        }
        ImageIO.write(image, "jpg", new File(path));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    native private static Point[] generate(int w, int h);
}
