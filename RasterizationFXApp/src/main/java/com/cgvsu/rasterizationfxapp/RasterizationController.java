package com.cgvsu.rasterizationfxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.AnchorPane;

import com.cgvsu.rasterization.*;
import javafx.scene.paint.Color;

public class RasterizationController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 200, 300, 200, 100, Color.CHOCOLATE);
        Rasterization.drawRectangle(canvas.getGraphicsContext2D(), 250, 250, 50, 200, Color.AQUA);

        PixelWriter pixelWriter = canvas.getGraphicsContext2D().getPixelWriter();

        Rasterization.drawDDALineWithInterpolation(pixelWriter, 50, 50, 200, 200, Color.RED, Color.BLUE);

        Rasterization.drawDDALineWithInterpolation(pixelWriter, 300, 300, 150, 100, Color.GREEN, Color.YELLOW);

        Rasterization.drawDDALineWithInterpolation(pixelWriter, 50, 350, 250, 350, Color.BLACK, Color.WHITE);

        Rasterization.drawDDALineWithInterpolation(pixelWriter, 350, 50, 350, 250, Color.MAGENTA, Color.CYAN);

        Rasterization.drawDDALineWithInterpolation(pixelWriter, 400, 400, 400, 400, Color.ORANGE, Color.ORANGE);
    }

}