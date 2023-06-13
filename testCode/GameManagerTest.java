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
        InetAddress serverIp;
        try {
            serverIp = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        //Starts the big server

        GameManager gameManager = new GameManager(hostPort, rounds);
        Client clientA = new Client(hostPort, serverIp);
        Client clientB = new Client(hostPort, serverIp);
        Scanner sc = new Scanner(System.in);
        int c = 2;

        System.out.println();

        HostTest.hostTest(c, gameManager);
        ClientTest.clientTest(c, gameManager, clientA);
        ClientTest.clientTest(3, gameManager, clientB);

        HostTest.hostTest(c, gameManager);
        ClientTest.clientTest(c, gameManager, clientA);
        ClientTest.clientTest(3, gameManager, clientB);

        HostTest.hostTest(4, gameManager);
        ClientTest.clientTest(4, gameManager, clientA);
        ClientTest.clientTest(3, gameManager, clientB);

        System.out.println("We are closing the game now.....:)");
        sc.close();
        gameManager.host.closeGame();
        facadeServer.close();
    }




//        while(Host.host.rounds != 0)
//        {
//            ..System.out.println("hostTest: ");
//            for (int i = 0; i < gameManager.host.playerTilesMap.get(1).size(); i++) {
//                System.out.print(gameManager.host.playerTilesMap.get(1).get(i).letter + " ");
//            }
//            System.out.println();
//            System.out.print("please enter your index: ");
//            //c = sc.nextInt();
//            HostTest.hostTest(4, gameManager);
//
//
//            System.out.println("clientTest: ");
//            for (int i = 0; i < gameManager.host.playerTilesMap.get(2).size(); i++) {
//                System.out.print(gameManager.host.playerTilesMap.get(2).get(i).letter+" ");
//            }
//            System.out.println();
//            //System.out.print("please enter your index: ");
//            //c = sc.nextInt();
//            //System.out.println();
//            ClientTest.clientTest(2, gameManager, client);
//
//            //////    added instead the rounds in placeWord for debugging   //////
//                gameManager.host.rounds--;
//            //////    added instead the rounds in placeWord for debugging   //////
//        }

}
