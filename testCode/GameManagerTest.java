package testCode;

import Model.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameManagerTest
{
    public static void main(String[] args)
    {
        int serverPort = 1234;
        int hostPort = 5678;
        int rounds = 2;

        FacadeServer facadeServer = new FacadeServer(serverPort);
        InetAddress serverIp = null;
        try {
            serverIp = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        //Starts the big server

        GameManager gameManager = new GameManager(serverIp, serverPort, hostPort, rounds);
        Client client = new Client(hostPort, serverIp);
        Scanner sc = new Scanner(System.in);
        int c = 2;

        System.out.println();

//        while(Host.host.rounds != 0)
//        {
//            System.out.println("hostTest: ");
//            for (int i = 0; i < gameManager.host.playerTilesMap.get(1).size(); i++) {
//                System.out.print(gameManager.host.playerTilesMap.get(1).get(i).letter + " ");
//            }
//            System.out.println();
//            System.out.print("please enter your index: ");
//            //c = sc.nextInt();
//            System.out.println();

            //HostTest.hostTest(c, gameManager);
            //HostTest.hostTest(4, gameManager);


            System.out.println("clientTest: ");
            for (int i = 0; i < gameManager.host.playerTilesMap.get(2).size(); i++) {
                System.out.print(gameManager.host.playerTilesMap.get(2).get(i).letter+" ");
            }

            System.out.println();
            System.out.print("please enter your index: ");
            //c = sc.nextInt();
            System.out.println();
            gameManager.host.turn++;
            System.out.println("the turn1: " + gameManager.host.turn);
            ClientTest.clientTest(c, gameManager, client);
            System.out.println("the turn2: " + gameManager.host.turn);
            ClientTest.clientTest(4, gameManager, client);
//         }
        System.out.println("flag");

//        Tile[] tilesA = new Tile[1];
//        tilesA[0] = gameManager.host.playerTilesMap.get(1).get(c);
//        Word word = new Word(tilesA, 7, 7, true);
//        gameManager.host.challenge(word.toString());

        sc.close();
        gameManager.host.closeGame();
        facadeServer.close();

//        try {
//            int serverPort = 1234;
//            int hostPort = 5678;
//            int rounds = 3;
//
//            FacadeServer facadeServer = new FacadeServer(serverPort);
//            InetAddress serverIp = InetAddress.getByName("localhost");
//            //Starts the server
//
//            GameManager gameManager = new GameManager(serverIp, serverPort, hostPort, rounds);
//            Client client = new Client(hostPort, serverIp);
//
//            System.out.println("host: " + gameManager.host.playerTilesMap.get(1));
//            System.out.println("client: " + gameManager.host.playerTilesMap.get(2));
//
//
//            Tile[] tilesA = new Tile[1];
//            Scanner sc = new Scanner(System.in);
//            char c = sc.next().charAt(0);
//            System.out.println(c);
//            //tilesA[0] = gameManager.host.playerTilesMap(c);
//            System.out.println(tilesA[0].letter);
//            Word wordA = new Word(tilesA, 7, 7, true);
//            gameManager.host.placeWord(wordA);
//
//
//            Tile[] tilesE = new Tile[1];
//            char c1 = sc.next().charAt(0);
//            System.out.println(c1);
//            tilesE[0] = gameManager.host.bag.getTile(c1);
//            Word wordE = new Word(tilesE, 7, 7, true);
//            client.placeWord(wordE);
//
//            gameManager.host.closeGame();
//            facadeServer.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
     }
}
