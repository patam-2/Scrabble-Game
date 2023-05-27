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
}
