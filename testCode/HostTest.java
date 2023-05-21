package testCode;
import Model.BookScrabbleHandler;
import Model.FacadeServer;
import Model.Host;
import Model.MyServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;

public class HostTest {
    public static void main(String[] args) {
        try {
            int serverPort = 1234;
            int hostPort = 5678;
            int rounds = 3;

            String s1[]=writeFile("s1.txt");
            String s2[]=writeFile("s2.txt");

            FacadeServer facadeServer = new FacadeServer(serverPort);
            // Start the server


            InetAddress serverIp = InetAddress.getByName("192.168.1.220");

            Host host = Host.getHost(serverIp, serverPort, hostPort, rounds);


            runClient(serverPort, "Q,s1.txt,s2.txt,"+s1[1], true);
            runClient(serverPort, "Q,s1.txt,s2.txt,"+s2[4], true);
            runClient(serverPort, "Q,s1.txt,s2.txt,2"+s1[1], false);
            runClient(serverPort, "Q,s1.txt,s2.txt,3"+s2[4], false);
            runClient(serverPort, "C,s1.txt,s2.txt,"+s1[9], true);
            runClient(serverPort, "C,s1.txt,s2.txt,#"+s2[1], false);

            // Perform actions or tests on the host object
            host.closeGame();
            try {Thread.sleep(2000);} catch (InterruptedException e) {}
            facadeServer.close();
            try {Thread.sleep(2000);} catch (InterruptedException e) {}
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


    public static String[] writeFile(String name)
    {
        Random r=new Random();
        String txt[]=new String[10];
        for(int i=0;i<txt.length;i++)
            txt[i]=""+(10000+r.nextInt(10000));

        try {
            PrintWriter out=new PrintWriter(new FileWriter(name));
            for(String s : txt) {
                out.print(s+" ");
            }
            out.println();
            out.close();
        }catch(Exception e) {}

        return txt;
    }


    public static void runClient(int port,String query,boolean result)
    {
        try
        {
            Socket server=new Socket("192.168.1.220",port);
            PrintWriter out=new PrintWriter(server.getOutputStream());
            Scanner in=new Scanner(server.getInputStream());
            out.println(query);
            out.flush();

            try {Thread.sleep(2000);} catch (InterruptedException e) {}
            String res=in.next();
            if((result && !res.equals("true")) || (!result && !res.equals("false")))
                System.out.println("problem getting the right answer from the server (-10)");
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("your code ran into an IOException (-10)");
        }
    }

}
