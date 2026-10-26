package com.cgvsu.rasterization;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Rasterization {

    public static void drawRectangle(
            final GraphicsContext graphicsContext,
            final int x, final int y,
            final int width, final int height,
            final Color color)
    {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        for (int row = y; row < y + height; ++row)
            for (int col = x; col < x + width; ++col)
                pixelWriter.setColor(col, row, color);
    }

    public static void drawDDALineWithInterpolation(
            PixelWriter pixelWriter,
            int x0, int y0, int x1, int y1,
            Color color0, Color color1) {

        double dx = x1 - x0;
        double dy = y1 - y0;

        int steps = (int) Math.max(Math.abs(dx), Math.abs(dy));

        if (steps == 0) {
            pixelWriter.setColor(x0, y0, color0);
            return;
        }

        double xIncrement = dx / steps;
        double yIncrement = dy / steps;

        double x = x0;
        double y = y0;

        for (int i = 0; i <= steps; i++) {
            int pixelX = (int) Math.round(x);
            int pixelY = (int) Math.round(y);

            double t = (double) i / steps;
            Color currentColor = interpolateColor(color0, color1, t);

            pixelWriter.setColor(pixelX, pixelY, currentColor);

            x += xIncrement;
            y += yIncrement;
        }
    }

    private static Color interpolateColor(Color c0, Color c1, double t) {
        double r = c0.getRed() + t * (c1.getRed() - c0.getRed());
        double g = c0.getGreen() + t * (c1.getGreen() - c0.getGreen());
        double b = c0.getBlue() + t * (c1.getBlue() - c0.getBlue());
        double opacity = c0.getOpacity() + t * (c1.getOpacity() - c0.getOpacity());

        return Color.color(r, g, b, opacity);
    }

}
