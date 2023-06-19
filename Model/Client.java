package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Client implements Player {

    public int id;
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
            out = null;
            out = new PrintWriter(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("your code ran into an IOException- In ranClient");
            return;
        }

        out.println("9 0");
        out.flush();

        while (in.hasNext()) {
            this.id = Integer.parseInt(in.next());
            if (in.hasNext()) {
                String input = in.next();
                char letter = input.charAt(0);
                tilesAmount.add(letter);
            }
        }

        Thread thread = new Thread(receiveMessage);
        thread.start();

      try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {throw new RuntimeException(e);}
        out = null;
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new Scanner(socket.getInputStream());

        } catch (IOException e) {throw new RuntimeException(e);}
        out.println("9 5");
        out.flush();
        in.close();
        out.close();
    }

    Runnable receiveMessage = ()-> {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(8000 + id);

            while (true) {

                // Create a byte array to store incoming data
                byte[] buffer = new byte[1024];

                // Create a DatagramPacket to receive the incoming data
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                System.out.println("i got it!");

                // Receive the packet
                datagramSocket.receive(packet);

                // Extract the received data from the packet
                byte[] receivedData = packet.getData();

                // Convert the received data to a string
                String message = new String(receivedData, 0, packet.getLength());

                // Display the received message
                System.out.println("Received Message: " + message);
            }

        } catch (Exception e) {
        } finally {
            // Close the socket
            datagramSocket.close();

        }
    };

    private void askQuery(String query) {
        try {
            this.socket = new Socket(ip,port); // from here jumps to the HostClientHandler
        } catch (IOException e) {
            System.out.println("your code ran into an IOException- In askQuery");
            return;
        }
        try {
            out = new PrintWriter(socket.getOutputStream());
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
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    @Override
    public void actionPlay(int type) {
    }

    public int placeWord(Word word) {
        this.askQuery(id + " " + "1," + word.toString());
        return 0;
    }

    public boolean challenge(String s) {
        this.askQuery(id + " " + "2," + s);
        if (in.next().equals("true"))
            return true;
        return false;
    }
}

