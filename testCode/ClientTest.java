package testCode;

import Model.*;

public class ClientTest {

    public static void clientTest(int c, GameManager gameManager, Client client)
    {
        Tile[] tilesA = new Tile[1];
        tilesA[0] = gameManager.host.playerTilesMap.get(2).get(c);
        System.out.println("Client- index: " + c);
        System.out.println("Client- the chosen letter:" + tilesA[0].letter);

        Word word = new Word(tilesA, 7, 7, true);
        boolean flag = gameManager.host.challenge(String.valueOf(tilesA[0].letter));
        System.out.println("client res for challenge: " + flag);
        System.out.println();

        //gameManager.host.turn++;
        client.placeWord(word);
    }
}

