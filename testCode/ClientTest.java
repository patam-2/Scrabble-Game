package testCode;

import Model.*;

public class ClientTest {
    public static void clientTest(int c, GameManager gameManager, Client client)
    {
        Tile[] tilesA = new Tile[1];
        tilesA[0] = gameManager.host.playerTilesMap.get(2).get(c);
        System.out.println("Client- index: " + c);
        System.out.println("Client- the chosen letter is: " + tilesA[0].letter);

        Word word = new Word(tilesA, 3, 3, true); // a words that doesn't override the hosts word
        boolean flag = gameManager.host.challenge(String.valueOf(tilesA[0].letter));
        System.out.println("Client result for challenge: " + flag);
        int res = client.placeWord(word);
        System.out.println("Client score for placeWord: " +res);
        System.out.println();
    }
}

