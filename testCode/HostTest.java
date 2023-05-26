package testCode;

import Model.*;

public class HostTest {

    public static void hostTest(int c, GameManager gameManager)
    {
        Tile[] tilesA = new Tile[1];
        tilesA[0] = gameManager.host.playerTilesMap.get(1).get(c);
        System.out.println("Host- index: " + c);
        System.out.println("Host- the chosen letter:" + tilesA[0].letter);

        Word word = new Word(tilesA, 7, 7, true);
        boolean flag = gameManager.host.challenge(String.valueOf(tilesA[0].letter));
        System.out.println("host res: " + flag);
        int res = gameManager.host.placeWord(word);
        System.out.println("the score is: "+res);
        System.out.println();
    }
}



