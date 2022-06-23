package com.example.genet_algoritm_gui;

//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;

//public class WindowRis {}
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WindowRis {
    static Tour the_best=null;// static pozvolyaet videt dlya vseh classov daze esli ih esche net

    @FXML
    private Canvas TheCanvas;

    @FXML
    public void initialize() {
        myPaint();
    }

    public void myPaint()
    {
        GraphicsContext gc = TheCanvas.getGraphicsContext2D();
        double w = TheCanvas.getWidth();
        double h = TheCanvas.getHeight();
        double mashtabX = (w-20)/WindowRis.the_best.getMaxX();
        double mashtabY = (h-20)/WindowRis.the_best.getMaxY();
        int xOld;
        int yOld;
        int xCurrent;
        int yCurrent;
        xCurrent = (int) (WindowRis.the_best.getCity(0).x*mashtabX);
        yCurrent = (int) (WindowRis.the_best.getCity(0).y*mashtabY);
        gc.setStroke(Color.BLUE);
        String s1 = Integer.toString(0);
                gc.strokeText(s1, xCurrent, yCurrent);
        for (int i = 1; i < WindowRis.the_best.tourSize(); i++) {
            xOld=xCurrent;
            yOld=yCurrent;
            xCurrent = (int) (WindowRis.the_best.getCity(i).x*mashtabX);
            yCurrent = (int) (WindowRis.the_best.getCity(i).y*mashtabY);
             s1 = Integer.toString(i);
            gc.setStroke(Color.BLUE);
            gc.strokeText(s1, xCurrent, yCurrent);
            gc.setStroke(Color.RED);
            gc.strokeLine(xOld,yOld,xCurrent,yCurrent);
        }

    }
}
