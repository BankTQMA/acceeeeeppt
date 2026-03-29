package com.lnw.acceeeeeppt.ui;

import java.awt.*;

public class FontPresets {
    private FontPresets() {
        /* This utility class should not be instantiated */
    }

    private static final String MAINFONTNAME = "Segoe UI";

    public static final Font PASSFAILFONT = new Font(MAINFONTNAME, Font.BOLD, 128);
    public static final Font H1BOLDFONT = new Font(MAINFONTNAME, Font.BOLD, 24);
    public static final Font H1PLAINFONT = new Font(MAINFONTNAME, Font.PLAIN, 24);
    public static final Font H2BOLDFONT = new Font(MAINFONTNAME, Font.BOLD, 20);
    public static final Font H2PLAINFONT = new Font(MAINFONTNAME, Font.PLAIN, 20);
    public static final Font H3PLAINFONT = new Font(MAINFONTNAME, Font.PLAIN, 16);
    public static final Font REGULARPLAINFONT = new Font(MAINFONTNAME, Font.PLAIN, 12);
    public static final Font REGULARBOLDFONT = new Font(MAINFONTNAME, Font.BOLD, 12);
    public static final Font REGULARITALICFONT = new Font(MAINFONTNAME, Font.ITALIC, 12);
}
