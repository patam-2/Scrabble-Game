package View;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class ClientBoardDisplayer extends Canvas
{
    public int col;
    public int row;
    public String[][] boardMat;
    public StringProperty basicPinkBackgroundSquare;
    public StringProperty purpleSquare;
    public StringProperty lightPinkSquare;
    public StringProperty yellowSquare;
    public StringProperty starSquare;
    public StringProperty darkPinkSquare;
    public HashMap<Character, String> lettersMap;

    public ClientBoardDisplayer()
    {
        this.col = 7;
        this.row = 7;
        basicPinkBackgroundSquare = new SimpleStringProperty();
        purpleSquare= new SimpleStringProperty();
        lightPinkSquare= new SimpleStringProperty();
        yellowSquare= new SimpleStringProperty();
        starSquare = new SimpleStringProperty();
        darkPinkSquare = new SimpleStringProperty();
        this.lettersMap = new HashMap<>();
        loadLetters();
    }

    private void loadLetters() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            String path = "C:/Users/ariel/OneDrive/Desktop/RunningTask_1/BookScabbleFacade/src/View/resources/"+String.valueOf(letter)+".jpg";
            this.lettersMap.put(letter, path);
        }
    }

    public void setRow(int row)
    {
        if (row == -1 || row == 15)
            return;
        this.row = row;
        redraw();
    }

    public void setCol(int col)
    {
        if (col == -1 || col == 15)
            return;
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

    public void redraw()
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
                basicPinkBackgroundSquareImg = new Image(new FileInputStream(basicPinkBackgroundSquare.get()));
                purpleSquareImg = new Image(new FileInputStream(purpleSquare.get()));
                lightPinkSquareImg = new Image(new FileInputStream(lightPinkSquare.get()));
                yellowSquareImg = new Image(new FileInputStream(yellowSquare.get()));
                starSquareImg = new Image(new FileInputStream(starSquare.get()));
                darkPinkSquareImg = new Image(new FileInputStream(darkPinkSquare.get()));

            } catch (FileNotFoundException e) {throw new RuntimeException(e);}


            gc.clearRect(0,0,getWidth(),getHeight());

            for (int i = 0; i < boardMat.length; i++)
            {
                for (int j = 0; j < boardMat[i].length; j++)
                {
                    if (boardMat[i][j].equals("3W")) {
                        gc.drawImage(purpleSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j].equals("2L"))
                    {
                        gc.drawImage(lightPinkSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j].equals("2W"))
                    {
                        gc.drawImage(yellowSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j].equals("3L"))
                    {
                        gc.drawImage(darkPinkSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j].equals("2WS"))
                    {
                        gc.drawImage(starSquareImg, j * w, i * h, w, h);
                    }

                    else if (boardMat[i][j].equals("0"))
                    {
                        gc.drawImage(basicPinkBackgroundSquareImg, j * w, i * h, w, h);
                    }

                    else {
                        Image newImage = new Image(lettersMap.get(boardMat[i][j].charAt(0)));
                        gc.drawImage(newImage,j * w, i * h, w, h);
                    }
                }
            }
            gc.setFill(Color.RED);
            gc.fillRect(col*w, row*h, w, h);
        }
    }

    public void changePhoto(char letter) {

        GraphicsContext gc = getGraphicsContext2D(); //Get the graphics context for the canvas
        double w = getWidth() / boardMat[0].length;
        double h = getHeight() / boardMat.length;
        gc.clearRect(col*w, row*h, w, h);
        Image newImage = new Image(lettersMap.get(letter));
        gc.drawImage(newImage, col*w, row*h, w, h);
    }

    public String getBasicPinkBackgroundSquare(){
        return basicPinkBackgroundSquare.get();
    }

    public String getPurpleSquare(){
        return purpleSquare.get();
    }

    public String getLightPinkSquare(){
        return lightPinkSquare.get();
    }

    public String getYellowSquare(){
        return yellowSquare.get();
    }

    public String getDarkPinkSquare(){
        return darkPinkSquare.get();
    }

    public String getStarSquare(){
        return starSquare.get();
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
}
