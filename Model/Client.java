package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Player
{
    public int id;
    boolean isConnected = false;
    public Socket socket;
    public InetAddress ip;
    public int port;
    public PrintWriter out;
    public Scanner in;
    public ArrayList<Character> tilesAmount = new ArrayList<>();

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
            return;
        }

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

        out.println("9 0");

        this.id = Integer.parseInt(in.next());
        while (in.hasNext())
        {
           String input = in.next();
           char letter = input.charAt(0);
           tilesAmount.add(letter);
        }
        isConnected = true;
    }

    public void askQuery(String query)
    {
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

    @Override
    public void actionPlay(int type) {

    }

    public int placeWord(Word word) {
        this.askQuery(id + " " + "1," + word.toString());

        if (in.next().equals("t")) {

            for(int i = 0; i < word.tiles.length; i++) {
                tilesAmount.remove(word.tiles[i].letter);
            }
            while (in.hasNext()) {
                String input = in.next();
                char letter = input.charAt(0);
                tilesAmount.add(letter);
            }
        }
        return 0;
    }

    public boolean challenge(String s) {
        this.askQuery(id + " " + "2," + s);
        if (in.next().equals("true"))
            return true;
        return false;
    }
}
