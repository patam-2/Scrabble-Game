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


        HostTest.hostTest(c, gameManager);
        //ClientTest.clientTest(c, gameManager, client);
        HostTest.hostTest(c, gameManager);
        HostTest.hostTest(4, gameManager);
        ClientTest.clientTest(4, gameManager, client);




//        while(Host.host.rounds != 0)
//        {
//            System.out.println("hostTest: ");
//            for (int i = 0; i < gameManager.host.playerTilesMap.get(1).size(); i++) {
//                System.out.print(gameManager.host.playerTilesMap.get(1).get(i).letter + " ");
//            }
//            System.out.println();
//            System.out.print("please enter your index: ");
//            //c = sc.nextInt();
//            //HostTest.hostTest(c, gameManager);
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
//            System.out.println();
//            //ClientTest.clientTest(c, gameManager, client);
//            ClientTest.clientTest(2, gameManager, client);
//        }

        System.out.println("flag");
        sc.close();
        gameManager.host.closeGame();
        facadeServer.close();
    }
}
