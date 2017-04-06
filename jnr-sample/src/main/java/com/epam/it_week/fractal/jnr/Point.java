package com.epam.it_week.fractal.jnr;

import java.awt.*;


/**
 * Created by Sergey Larin on 15.03.2017.
 */
public class Point extends jnr.ffi.Struct {
    public Signed32 x = new Signed32();
    public Signed32 y = new Signed32();
    public Boolean b =  new Boolean();

    private static final Color
            WHITE = new Color(255, 255, 255),
            IT_WEEK_GREEN = new Color(136, 195, 65);

    public Point(jnr.ffi.Runtime runtime) {
        super(runtime);
    }

    public Color getColor() {
        return b.get() ? IT_WEEK_GREEN : WHITE;
    }
}
