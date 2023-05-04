package test;


import java.util.Arrays;
import java.util.Objects;

public class Word
{
   public  Tile[] tiles = null;
   public int row;
   public int col;
   public boolean vertical;

    public Word(Tile[] t, int r, int c, boolean vertic)
    {
        this.tiles = t;
        this.row = r;
        this.col = c;
        this.vertical = vertic;
    }

   public Tile[] getTilesArr()
    {
        return this.tiles;
    }

   public int getRow()
    {
        return this.row;
    }

   public int getCol()
   {
        return this.col;
   }

   public boolean getIsVertical()
   {
       return this.vertical;
   }



    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof Word))
            return false;

        Word word = (Word) o;

        return getRow() == word.getRow() && getCol() == word.getCol() && vertical == word.vertical && Arrays.equals(tiles, word.tiles);
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(getRow(), getCol(), vertical);
        result = 31 * result + Arrays.hashCode(tiles);
        return result;
    }

}

