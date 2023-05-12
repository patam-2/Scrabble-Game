package Model;

import java.util.ArrayList;


public class Board
{
    private static Board board = null;
    private static Tile[][] matTile;
    private static String[][] bonusMat;
    private ArrayList<Word> wordsOnBoard = new ArrayList<>();

    private Board()
    {
        matTile = new Tile[15][15];
        bonusMat = new String[][]{
                {"3W", "0", "0", "2L", "0", "0", "0", "3W", "0", "0", "0", "2L", "0", "0", "3W"},
                {"0", "2W", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "2W", "0"},
                {"0", "0", "2W", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2W", "0", "0"},
                {"2L", "0", "0", "2W", "0", "0", "0", "2L", "0", "0", "0", "2W", "0", "0", "2L"},
                {"0", "0", "0", "0", "2W", "0", "0", "0", "0", "0", "2W", "0", "0", "0", "0"},
                {"0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0"},
                {"0", "0", "2L", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2L", "0", "0"},
                {"3W", "0", "0", "2L", "0", "0", "0", "2WS", "0", "0", "0", "2L", "0", "0", "3W"},
                {"0", "0", "2L", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2L", "0", "0"},
                {"0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0"},
                {"0", "0", "0", "0", "2W", "0", "0", "0", "0", "0", "2W", "0", "0", "0", "0"},
                {"2L", "0", "0", "2W", "0", "0", "0", "2L", "0", "0", "0", "2W", "0", "0", "2L"},
                {"0", "0", "2W", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2W", "0", "0"},
                {"0", "2W", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "2W", "0"},
                {"3W", "0", "0", "2L", "0", "0", "0", "3W", "0", "0", "0", "2L", "0", "0", "3W"},
        };

    }


    public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }

        return board;
    }


    public Tile[][] getTiles() {
        Tile[][] newTileMat = new Tile[15][15];

        for (int i = 0; i < matTile.length; i++) {
            for (int j = 0; j < matTile.length; j++) {
                if (matTile[i][j] != null) {
                    newTileMat[i][j] = matTile[i][j];
                } else
                    newTileMat[i][j] = null;
            }
        }

        return newTileMat;
    }



    public boolean dictionaryLegal(Tile[] word) {return true;}


    public String getScoreOnBoard(int row, int col) {return bonusMat[row][col];}


    public int getScore(Word word)
    {
        int count3W = 0;
        int count2W = 0;
        int flag3W = 0;
        int flag2W = 0;
        int score = 0;
        int wordLength = word.tiles.length;
        int row = word.getRow();
        int col = word.getCol();
        String toCheck = null;



        if (word.getIsVertical())
        {
            for (; row < word.getRow() + wordLength; row++) {

                toCheck = getScoreOnBoard(row, col);

                if (toCheck != null)
                {

                    if (toCheck.equals("2L")) {
                        score += matTile[row][col].score * 2;
                    }


                    if (toCheck.equals("3L")) {
                        score += matTile[row][col].score * 3;
                    }


                    if (toCheck.equals("2W")) {
                        flag2W = 1;
                        count2W++;
                    }

                    if (toCheck.equals("2WS")) {
                        score += matTile[row][col].score;
                    }

                    if (toCheck.equals("3W")) {
                        flag3W = 1;
                        count3W++;
                    }

                    if (toCheck.equals("0"))
                    {
                        score += matTile[row][col].score;
                    }
                }
            }
        }

        else
        {
            for (; col < word.getCol() + wordLength; col++)
            {

                toCheck = getScoreOnBoard(row, col);

                if (toCheck != null) {
                    if (toCheck.equals("2L")) {
                        score += matTile[row][col].score * 2;
                    }


                    if (toCheck.equals("3L")) {
                        score += matTile[row][col].score * 3;
                    }


                    if (toCheck.equals("2W"))
                    {
                        score += matTile[row][col].score;
                        flag2W = 1;
                        count2W++;
                    }


                    if (toCheck.equals("2WS")) {
                        score += matTile[row][col].score;
                    }

                    if (toCheck.equals("3W")) {
                        score +=  matTile[row][col].score;
                        flag3W = 1;
                        count3W++;
                    }

                    if (toCheck.equals("0"))
                    {
                        score += matTile[row][col].score;
                    }
                }

            }
        }

        if (flag3W == 1)
            score *= count3W * 3;

        if (flag2W == 1)
            score *= count2W * 2;

        return score;
    }


    public int getLeftLetterCount(Word w)
    {

        int leftCount = 0;
        int i = 0;

        for (i = 0; i < w.tiles.length; i++)
        {
            leftCount++;
            if (w.tiles[i] == null) {
                leftCount--;
                break;
            }
        }

        if (i == w.tiles.length) {
            leftCount = 0;
        }

        return leftCount;
    }



    public boolean boardLegal(Word word)
    {
        int i = 0;
        int wordRow = word.getRow();
        int wordCol = word.getCol();
        boolean isItVertical = word.getIsVertical();



        if (word.tiles == null)
            return false;


        for (int s = 0; s < word.tiles.length; s++)
        {

            if (isItVertical)
            {
                if (wordRow < 0 || wordRow >= 15 || wordCol < 0 || wordCol >= 15)
                    return false;

                wordRow++;
            }

            else
            {
                if (wordRow < 0 || wordRow >= 15 || wordCol < 0 || wordCol >= 15)
                    return false;

                wordCol++;
            }
        }


        wordRow = word.getRow();
        wordCol = word.getCol();


        if (wordsOnBoard.size() == 0)
        {
            for (int k = 0; k < word.tiles.length; k++)
            {
                if (word.getIsVertical())
                {
                    if (wordRow == 7 && wordCol == 7)
                        return true;

                    wordRow++;
                }

                else
                {
                    if (wordRow == 7 && wordCol == 7)
                        return true;

                    wordCol++;
                }
            }
            return false;
        }



        for (wordRow = word.getRow(); wordRow < matTile.length; wordRow++)
        {
            for (wordCol = word.getCol(), i = 0; wordCol < matTile.length && i < word.tiles.length; wordCol++, i++)
            {
                if (matTile[wordRow][wordCol] != null && word.tiles[i] == null)
                    return true;

                else if (wordRow - 1 >= 0 && wordRow + 1 < 15 && wordCol - 1 >= 0 && wordCol + 1 < 15)
                {
                    if (word.getIsVertical())
                    {
                        if (matTile[wordRow][wordCol - 1] != null || matTile[wordRow][wordCol + 1] != null)
                            return true;

                    }
                    else
                        if (matTile[wordRow - 1][wordCol] != null || matTile[wordRow + 1][wordCol] != null )
                             return true;

                }
            }
        }

        return false;
    }




    public ArrayList<Word> getWords(Word word)
    {
        int leftCount;

        if (dictionaryLegal(word.tiles))
        {

            leftCount = getLeftLetterCount(word);

            if (leftCount == 0)    //The letter is the first letter in the word
                placeWordFromFirstLetter(word.getRow(), word.getCol(), word);


            if (leftCount == word.tiles.length - 1)    //The letter is the last letter in the word
                placeWordFromLastLetter(word.getRow(), word.getCol(), word);


            else if (leftCount > 0 && leftCount < word.tiles.length - 1) // The letter is one of the middle letters
            {
                placeWordFromMiddleLetter(word);
            }

        }

        return  areThereWordsAroundMe(word);
    }



    public void placeWordFromFirstLetter(int row, int col, Word w)
    {
        int startRow = row;
        int startCol = col;

        if (w.getIsVertical() && (startRow + w.tiles.length < 15))
        {
            for (int i = 0; i < w.tiles.length; i++, startRow++)
            {
                if (matTile[startRow][col] == null)
                    matTile[startRow][col] = w.tiles[i];

                else if (matTile[startRow][col] == w.tiles[i])
                    break;
            }
        }

        else
        {
            if (startCol + w.tiles.length < 15)
            {
                for (int i = 0; i < w.tiles.length; i++, startCol++) {
                    if (matTile[row][startCol] == null)
                        matTile[row][startCol] = w.tiles[i];

                    else if (matTile[row][startCol] == w.tiles[i])
                        break;
                }
            }
        }
    }



    public void placeWordFromMiddleLetter(Word w)
    {
        int startRow = w.getRow(); // The row letter index in mat already exist in mat
        int startCol = w.getCol(); // thr col letter index in mat already exist in mat
        int theInsideIndex = 0;
        int j = 0;
        int i = 0;


        for (j = 0; j < w.tiles.length; j++)
        {
            theInsideIndex++;

            if (w.tiles[j] == null) {
                theInsideIndex--;
                break;
            }

        }


        if (w.getIsVertical())
        {
            for (i = 0; i < theInsideIndex && startRow >= 0 && startRow < w.getRow() + theInsideIndex; i++, startRow++)
            {
                if (matTile[startRow][startCol] == null)
                    matTile[startRow][startCol] = w.tiles[i];

                else if (matTile[startRow][startCol] != null && w.tiles[i] == null)
                    break;
            }


            for (i = theInsideIndex + 1, startRow = startRow + 1; i < w.tiles.length && startRow < 15 && startRow < w.tiles.length + w.getRow(); i++, startRow++)
            {

                if (matTile[startRow][startCol] == null)
                    matTile[startRow][startCol] = w.tiles[i];

                else if (matTile[startRow][startCol] != null &&  w.tiles[i] == null)
                    break;
            }
        }

        else
        {

            for (j = 0; j < theInsideIndex && startCol >= 0 && startCol < w.getCol() + theInsideIndex; j++, startCol++) {
                if (matTile[startRow][startCol] == null)
                    matTile[startRow][startCol] = w.tiles[j];

                else if (matTile[startRow][startCol] != null && w.tiles[j] == null)
                    break;
            }

            startCol = startCol + 1;

            for (j = theInsideIndex + 1; j < w.tiles.length && startCol < 15 && startCol < w.tiles.length + w.getCol(); j++, startCol++)
            {
                if (matTile[startRow][startCol] == null)
                    matTile[startRow][startCol] = w.tiles[j];

                else if (matTile[startRow][startCol] != null && w.tiles[j] == null)
                    break;
            }
        }
    }


    public void placeWordFromLastLetter(int row, int col, Word w) {
        int startRow = row - 1;
        int startCol = col - 1;

        if (w.getIsVertical() && ((startRow + 2) - w.tiles.length >= 0)) {
            for (int i = 0; i < w.tiles.length; i++, startRow--) {
                if (matTile[startRow][col] == null)
                    matTile[startRow][col] = w.tiles[i];

                else if (matTile[startRow][col].letter == w.tiles[i].letter)
                    break;

            }
        } else {
            if ((startCol + 2) - w.tiles.length >= 0) {
                for (int i = 0; i < w.tiles.length; i++, startCol--) {
                    if (matTile[row][startCol] == null)
                        matTile[row][startCol] = w.tiles[i];

                    else if (matTile[row][startCol].letter == w.tiles[i].letter)
                        break;
                }
            }
        }
    }




    public ArrayList<Word> areThereWordsAroundMe(Word w)
    {
        ArrayList<Word> wordAroundMe = new ArrayList<Word>();
        int t;
        int count = 0;
        int jc, ic;
        int i = w.getRow();
        int j = w.getCol();
        int k = 0;
        int startRow = 0;
        int startCol = 0;
        int foundANewWordButNotLegal = 0;


        if (w.getIsVertical())
        {
            for (j = w.getCol(),  k = 0; k < w.tiles.length; k++, i++)
            {
                if (j < 15 && j >= 0 && j - 1 >= 0 && j + 1 < 15)
                {
                    if (matTile[i][j-1] == null && matTile[i][j+1] != null)
                    {
                        jc = j;
                        startCol = j;

                        while (matTile[i][jc] != null && jc < 15)
                        {
                            count++;
                            jc++;
                        }

                        Tile[] nw1 = new Tile[count];
                        t = 0;
                        jc = j;

                        while (t < count)
                        {
                            nw1[t] = matTile[i][jc];
                            jc++;
                            t++;
                        }


                        if (dictionaryLegal(nw1))
                        {
                            Word newWord = new Word(nw1, i, startCol, false);
                            wordAroundMe.add(newWord);
                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;
                    }


                    else if (matTile[i][j - 1] != null && matTile[i][j + 1] == null)
                    {
                        jc = j;

                        while (matTile[i][jc] != null && jc >= 0) {
                            count++;
                            jc--;
                        }

                        jc++;
                        startCol = jc;
                        Tile[] nw2 = new Tile[count];
                        t = 0;

                        while (t < count) {
                            nw2[t] = matTile[i][jc];
                            jc++;
                            t++;
                        }


                        if (dictionaryLegal(nw2))
                        {
                            Word newWord = new Word(nw2, i, startCol, false);
                            wordAroundMe.add(newWord);
                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;

                    }

                    else if (matTile[i][j - 1] != null && matTile[i][j + 1] != null)
                    {
                        jc = j;

                        while (matTile[i][jc] != null && jc >= 0) {
                            jc--;
                        }

                        jc++;
                        startCol = jc;


                        while (matTile[i][jc] != null && jc < 15) {
                            count++;
                            jc++;
                        }

                        jc = startCol;
                        Tile[] nw3 = new Tile[count];
                        t = 0;

                        while (t < count) {
                            nw3[t] = matTile[i][jc];
                            jc++;
                            t++;
                        }


                        if (dictionaryLegal(nw3))
                        {
                            Word newWord = new Word(nw3, i, startCol, false);
                            wordAroundMe.add(newWord);
                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;
                    }
                }
            }
        }

        else // The word is not vertical
        {
            for (i = w.getRow(), k = 0; k < w.tiles.length; k++, j++)
            {

                if (i < 15 && i >= 0 && i - 1 >= 0 && i + 1 < 15) {

                    if (matTile[i - 1][j] == null && matTile[i + 1][j] != null)
                    {
                        ic = i;
                        startRow = i;

                        while (matTile[ic][j] != null && ic < 15) {
                            count++;
                            ic++;
                        }

                        Tile[] nw1 = new Tile[count];
                        t = 0;
                        ic = i;

                        while (t < count && ic < 15) {
                            nw1[t] = matTile[ic][j];
                            ic++;
                            t++;
                        }


                        if (dictionaryLegal(nw1))
                        {
                            Word newWord = new Word(nw1, startRow, j, true);
                            wordAroundMe.add(newWord);
                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;
                    }


                    else if (matTile[i - 1][j] != null && matTile[i + 1][j] == null) {
                        ic = i;

                        while (matTile[ic][j] != null && ic >= 0) {
                            count++;
                            ic--;
                        }

                        Tile[] nw2 = new Tile[count];
                        t = 0;
                        ic++;
                        startRow = ic;

                        while (t < count)
                        {
                            nw2[t] = matTile[ic][j];
                            ic++;
                            t++;
                        }


                        if (dictionaryLegal(nw2)) {
                            Word newWord = new Word(nw2, startRow, j, true);
                            wordAroundMe.add(newWord);
                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;
                    }


                    else if (matTile[i - 1][j] != null && matTile[i + 1][j] != null)
                    {

                        ic = i;

                        while (matTile[ic][j] != null && ic >= 0) {
                            ic--;
                        }

                        ic++;
                        startRow = ic;


                        while (matTile[ic][j] != null && ic < 15) {
                            count++;
                            ic++;
                        }

                        ic = startRow;
                        Tile[] nw3 = new Tile[count];
                        t = 0;

                        while (t < count)
                        {
                            nw3[t] = matTile[ic][j];
                            ic++;
                            t++;
                        }


                        if (dictionaryLegal(nw3)) {
                            Word newWord = new Word(nw3, startRow, j, true);
                            wordAroundMe.add(newWord);

                        }

                        else
                            foundANewWordButNotLegal = 1;

                        count = 0;
                    }
                }
            }
        }

        if (foundANewWordButNotLegal == 1)
            return null;

        return wordAroundMe;
    }



    public int tryPlaceWord(Word word)
    {
       boolean t = boardLegal(word);
       int score = 0;
       int row = word.getRow();
       int col = word.getCol();
       int flag = 0;

       if (t)
       {
           if (matTile[7][7] == null)
           {
               for (int i = 0; i < word.tiles.length; i++)
               {
                   matTile[row][col] = word.tiles[i];

                   if (word.getIsVertical())
                        row++;

                   else
                       col++;

               }

               wordsOnBoard.add(word);
               return 2*getScore(word);
           }

           else
           {

               if (getWords(word) == null)
                   return score;


               int wordsSize = getWords(word).size();
               int i = 0;

                for (int p = 0; p < wordsSize; p++)
                {
                    Word tempWord = getWords(word).get(p);
                    dictionaryLegal(tempWord.tiles);
                    flag = 0;

                    for (i = 0; i < wordsOnBoard.size() && flag == 0; i++)
                    {
                        for (int s = 0; s < tempWord.tiles.length && s < wordsOnBoard.get(i).tiles.length && flag == 0;)
                        {
                            if (wordsOnBoard.get(i).tiles[s] == tempWord.tiles[s])
                            {
                                if (s + 1 == tempWord.tiles.length)
                                {
                                    flag = 1;
                                    i++;
                                }
                            }
                            s++;
                        }

                    }

                    if (flag == 0 && i == wordsOnBoard.size())
                    {
                        score += getScore(tempWord);
                        wordsOnBoard.add(tempWord);
                    }
                }
               wordsOnBoard.add(word);
               score += getScore(word);
           }
       }

       return score;
    }
}

