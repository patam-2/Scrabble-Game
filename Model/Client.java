package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Player
{
    public Socket socket;
    public InetAddress ip;
    public int port;
    public PrintWriter out;
    public Scanner in;


    public Client(int port, InetAddress ip) {
        this.ip = ip;
        this.port = port;
        this.runClient();
    }

    public void runClient() //opens the Socket
    {
        try {
           this.socket = new Socket(ip,port);

        } catch (IOException e) {
            System.out.println("your code ran into an IOException");
        }
    }

    public void askQuery(String query) {

        out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
           in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        out.println(query);
        out.flush();
    }

    public void closeClient() {
        try {
            in.close();
            out.close();
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int placeWord(Word word) {
        this.askQuery("1"+word.toString());
        return 0;
    }

    public boolean challenge(String s) {
        this.askQuery("2"+s);
        return true;
    }

    public int[] getTiles(){
        this.askQuery("3");
        return null;
    }
}
