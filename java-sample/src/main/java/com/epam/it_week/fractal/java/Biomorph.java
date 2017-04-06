package com.epam.it_week.fractal.java;

import org.apache.commons.math3.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey Larin on 15.03.2017.
 */

public class Biomorph {
    private static final int RANGE = 80;
    private static final int MAX = 50;
    private static final double INDEX = 0.01;
    private static final double RE_C = 1.07;
    private static final double IM_C = 0.0001;
    private static final Color
            WHITE = new Color(255, 255, 255),
            IT_WEEK_GREEN = new Color(136, 195, 65);

    private final Point[] pointMap;
    private final int width;
    private final int height;

    public Biomorph(int w, int h) {
        width = w;
        height = h;
        pointMap = generate(w, h);
    }

    private Point[] generate(int w, int h) {
        Point[] image = new Point[w * h];
        final Complex constant = new Complex(RE_C, IM_C);
        Complex z;
        double re_z, im_z;
        int k = 0;
        for (int x = -w / 2; x < w / 2; x++) {
            for (int y = -h / 2; y < h / 2; y++) {
                z = new Complex(x * INDEX, y * INDEX);
                re_z = Math.abs(z.getReal());
                im_z = Math.abs(z.getImaginary());
                for (int i = 0; (re_z < RANGE || im_z < RANGE || z.abs() < RANGE) && i < MAX; i++) {
                    z = z.pow(3).add(constant);
                    re_z = Math.abs(z.getReal());
                    im_z = Math.abs(z.getImaginary());
                }
                Color c = re_z < MAX || im_z < MAX * 50 ? WHITE : IT_WEEK_GREEN;
                Point p = new Point(x + w / 2, y + h / 2, c);
                image[k] = p;
                k++;
            }
        }
        return image;
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
}
