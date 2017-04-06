package com.epam.it_week.fractal.jna;

import com.sun.jna.Structure;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Point extends Structure {
    private static final Color
            WHITE = new Color(255, 255, 255),
            IT_WEEK_GREEN = new Color(136, 195, 65);

    public int x;
    public int y;
    public boolean b;

    public Point() {
        super();
    }

    public Color getColor() {
        return b ? IT_WEEK_GREEN : WHITE;
    }

    @Override
    protected List<String> getFieldOrder() {
        return Arrays.asList("x", "y", "b");
    }

    public static class ByReference extends Point implements Structure.ByReference {
    }
}

