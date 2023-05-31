package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BoardDisplayer extends Canvas
{
    public int col;
    public int row;
    public String[][] boardMat;
    private StringProperty basicPinkBackgroundSquare;
    public StringProperty purpleSquare;
    public StringProperty lightPinkSquare;
    public StringProperty yellowSquare;
    public StringProperty starSquare;
    public StringProperty darkPinkSquare;


    public BoardDisplayer()
    {
        this.col = 7;
        this.row = 7;
        basicPinkBackgroundSquare = new SimpleStringProperty();
        purpleSquare= new SimpleStringProperty();
        lightPinkSquare= new SimpleStringProperty();
        yellowSquare= new SimpleStringProperty();
        starSquare = new SimpleStringProperty();
        darkPinkSquare = new SimpleStringProperty();
    }

    public void setRow(int row)
    {
        this.row = row;
        redraw();
    }

    public void setCol(int col)
    {
        this.col = col;
        redraw();
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setBoardMat(String[][] boardMat) {
        this.boardMat = boardMat;
        redraw();
    }

    private void redraw()
    {
        if (boardMat != null)
        {
            double w = getWidth() / boardMat[0].length;
            double h = getHeight() / boardMat.length;
            GraphicsContext gc = getGraphicsContext2D(); //get the graphics context for the canvas
            Image basicPinkBackgroundSquareImg = null;
            Image purpleSquareImg = null;
            Image lightPinkSquareImg = null;
            Image yellowSquareImg = null;
            Image starSquareImg = null;
            Image darkPinkSquareImg = null;


            try {
                basicPinkBackgroundSquareImg = new Image(new FileInputStream(getBasicPinkBackgroundSquareName()));
                purpleSquareImg = new Image(new FileInputStream(getPurpleSquareName()));
                lightPinkSquareImg = new Image(new FileInputStream(getLightPinkSquareName()));
                yellowSquareImg = new Image(new FileInputStream(getYellowSquareSquareName()));
                starSquareImg = new Image(new FileInputStream(getStarSquareName()));
                darkPinkSquareImg = new Image(new FileInputStream(getDarkPinkSquareSquareName()));

            } catch (FileNotFoundException e) {throw new RuntimeException(e);}


            gc.clearRect(0,0,getWidth(),getHeight());

            for (int i = 0; i < boardMat.length; i++)
            {
                for (int j = 0; j < boardMat[i].length; j++)
                {
                    if (boardMat[i][j] != "3W") {
                        gc.drawImage(purpleSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j] != "2L")
                    {
                        gc.drawImage(lightPinkSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j] != "2W")
                    {
                        gc.drawImage(yellowSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j] != "3L")
                    {
                        gc.drawImage(darkPinkSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j] != "2WS")
                    {
                        gc.drawImage(starSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j] != "0")
                    {
                        gc.drawImage(basicPinkBackgroundSquareImg, j * w, i * h, w, h);
                    }
                }
            }
            gc.setFill(Color.RED);
            gc.fillRect(col*w, row*w+7, w, h-2);
        }
    }

    public String getBasicPinkBackgroundSquareName(){
        return basicPinkBackgroundSquare.get();
    }

    public String getPurpleSquareName(){
        return purpleSquare.get();
    }

    public String getLightPinkSquareName(){
        return lightPinkSquare.get();
    }

    public void setBasicPinkBackgroundSquare(String basicPinkBackgroundSquare) {
        this.basicPinkBackgroundSquare.set(basicPinkBackgroundSquare);
    }

    public void setPurpleSquare(String purpleSquare) {
        this.purpleSquare.set(purpleSquare);
    }

    public void setLightPinkSquare(String lightPinkSquare) {
        this.lightPinkSquare.set(lightPinkSquare);
    }

    public void setYellowSquare(String yellowSquare) {
        this.yellowSquare.set(yellowSquare);
    }

    public void setStarSquare(String starSquare) {
        this.starSquare.set(starSquare);
    }

    public void setDarkPinkSquare(String darkPinkSquare) {
        this.darkPinkSquare.set(darkPinkSquare);
    }

    public String getYellowSquareSquareName(){
        return yellowSquare.get();
    }

    public String getDarkPinkSquareSquareName(){
        return darkPinkSquare.get();
    }

    public String getStarSquareName(){
        return starSquare.get();
    }
}
