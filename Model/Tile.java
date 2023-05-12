package Model;

import java.util.Objects;
import java.util.Random;

public class Tile
{

    public final char letter;
    public final int score;


    private Tile(char letter, int score)
    {
        this.letter = letter;
        this.score = score;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (!(o instanceof Tile))
            return false;

        Tile tile = (Tile) o;

        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(letter, score);
    }



    public static class Bag
    {
        int AvalTilesOp;
        int[] tilesCounts = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        Tile[] tilesArry = new Tile[26];
        private static Bag bag = null;

        private Bag()
        {
            AvalTilesOp = 26;
            setTiles(this.tilesArry);
        }


        public Tile getRand()
        {

            char[] randLettersArray = new char[AvalTilesOp];

            for (int j = 0; j < tilesArry.length; j++)
            {
                if (getTileCounts(tilesArry[j].letter) > 0)
                    randLettersArray[j] = tilesArry[j].letter;

            }

            Random generator = new Random();
            int randomIndex = generator.nextInt(randLettersArray.length);

            tilesCounts[randomIndex] -= 1;

            return tilesArry[randomIndex];
        }


        public Tile getTile(char letter)
        {
            for (int i = 0; i < AvalTilesOp; i++)
            {
                if (tilesArry[i].letter == letter && tilesCounts[i] > 0)
                {
                    tilesCounts[i] -= 1;
                    return tilesArry[i];
                }
            }
            return null;
        }


        public void put(Tile t)
        {
            for (int i = 0; i < tilesArry.length; i++)
            {
                if(tilesArry[i].letter == t.letter && size() < 98)
                {
                    tilesCounts[i] += 1;
                }
            }
        }


        public int size()
        {
            int bagLetters = 0;
            for (int i = 0; i < AvalTilesOp; i++)
                bagLetters += tilesCounts[i];

            return bagLetters;
        }


        public int[] getQuantities()
        {
            int[] newTilesCount = new int[AvalTilesOp];

            for(int i = 0; i < AvalTilesOp; i++)
                newTilesCount[i] = tilesCounts[i];

            return newTilesCount;
        }


        public static Bag getBag()
        {
            if (bag == null)
            {
                bag = new Bag();
            }
            return bag;
        }


       public int getTileScore(char t)
        {
            int index = getTileCounts(t);

            if (index == 0 || index == 4 || index == 8 || index == 11 || index == 13 ||
                    index== 14|| index == 17 || index == 18 || index == 19 || index == 20)
            {
                return 1;
            }

            if (index == 3 || index == 6)
                return 2;

            if (index == 1 || index == 2 || index == 12 || index == 15)
                return 3;

            if (index == 5 || index == 7 || index == 21 || index == 22 || index == 24)
                return 4;

            if (index == 10)
                return 5;

            if (index == 9 || index == 23)
                return 8;

            else  //(index == 16 || index == 25)
                return 10;
        }


        public Tile[] setTiles(Tile[] tA)
        {
            char c = 'A';
            int score;

           for (int i = 0; i < tilesArry.length && c <= 'Z'; i++, c++)
           {
               score = getTileScore(c);
               tilesArry[i] = new Tile(c,score);
           }

           return tilesArry;
        }


       public int getTileCounts(char letter)
        {
            int index = letter - 'A';
            if (index >= 0 && index < 26)
                return index;

            return -1;
        }
    }
}


