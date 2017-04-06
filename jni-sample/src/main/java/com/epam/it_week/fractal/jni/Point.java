package com.epam.it_week.fractal.jni;

import java.awt.*;

/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Point {
    private final int x, y;
    private final boolean b;

    private static final Color
            WHITE = new Color(255, 255, 255),
            IT_WEEK_GREEN = new Color(136, 195, 65);

    public Point(int x, int y, boolean b) {
        this.x = x;
        this.y = y;
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return b ? IT_WEEK_GREEN : WHITE;
    }
}
