package com.epam.it_week.fractal.java;

import java.awt.*;

/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Point{
    private final int x;
    private final int y;
    private final Color c;

    public Point(int x, int y, Color c) {
        super();
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return c;
    }
}

