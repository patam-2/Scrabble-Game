package Model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class HostClientHandler implements ClientHandler
{
    private PrintWriter out;
    private Scanner in;
    public ArrayList<String> clientsIPlist = new ArrayList<>();

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {

        System.out.println("Just Visited HostClientHandler!");
        in = new Scanner(inFromclient);
        out = new PrintWriter(outToClient);

        if (in !=  null && in.hasNext()) {

            String input = in.next();
            int id = Integer.parseInt(input);
            input = in.next();
            char question = input.charAt(0);

            if (question == '0') {

                ArrayList<Tile> tiles = new ArrayList<>();
                String clientIp = "localhost";
                clientsIPlist.add(clientIp);
                Host.host.incrementNumberOfClients();

                for (int i = 0; i < 7; i++) {
                    Tile t = Host.host.bag.getRand();
                    tiles.add(t);
                }

                Host.host.playerTilesMap.put(Host.host.numberOfClients, tiles);
                String s = String.valueOf(Host.host.numberOfClients);
                System.out.println("Last Stop Is HostClientHandler- Number Of Clients: " + s);
                System.out.println();
                out.println(s);
            }

            ///////////// A NEW CHANGE FOR BROADCAST
            if (question == '5') {
                String s = String.valueOf(Host.host.numberOfClients);
                broadcast(s);
            }

             if (Host.host.turn == id) {
                if (question == '1') //if we put a word, we take back from the bag the amount of tiles in the word
                {
                    input = input.substring(2);
                    String[] newClientInputParts = input.split(",");
                    boolean vertical;
                    int row;
                    int col;

                    if (newClientInputParts[0].equals("V"))
                        vertical = true;
                    else
                        vertical = false;

                    row = Integer.parseInt(newClientInputParts[1]);
                    col = Integer.parseInt(newClientInputParts[2]);

                    char[] array = newClientInputParts[3].toCharArray();
                    Tile[] tiles = new Tile[array.length];

                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < Host.host.playerTilesMap.get(id).size(); j++)
                        {
                            if (Host.host.playerTilesMap.get(id).get(j).letter == array[i])
                                tiles[i] = Host.host.playerTilesMap.get(id).get(j);
                        }
                    }
                    Word word = new Word(tiles, row, col, vertical);
                    int score = Host.host.placeWord(word);
                    String s = "";

                    if (score != 0) {
                        s += "t";

                        for (int i = 0; i < word.tiles.length; i++) {
                            Host.host.playerTilesMap.get(id).remove(word.tiles[i]);
                        }

                        for (int i = 0; i < array.length; i++) {
                            Tile t = Host.host.bag.getRand();
                            Host.host.playerTilesMap.get(id).add(t);
                        }
                        Host.host.turn = 1 + (Host.host.turn % Host.host.numberOfClients);

                        if (id == Host.host.numberOfClients) {
                            Host.host.setNumberOfRounds(Host.host.getNumberOfRounds() - 1);
                            if (Host.host.getNumberOfRounds() == 0)
                                Host.host.closeGame();
                        }
                        Host.host.updateAndNotify();
                    }
                    else {
                        s += "f";
                    }
                    out.println(s);
                }

                if (question == '2') { // Challenge
                    String flag = String.valueOf(Host.host.challenge(input.substring(2)));
                    out.println(flag);
                }
            }
            else if (question != '0') {
                System.out.println("Sorry it's not your turn.");
            }
            out.flush();
        }
    }

    public void broadcast(String message) {

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();

            for (int i = 0; i < clientsIPlist.size(); i++) {
                String clientIP = clientsIPlist.get(i);
                int port = 8000 + i + 2;
                InetAddress ip = InetAddress.getByName(clientIP);
                DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.getBytes().length, ip, port);
                datagramSocket.send(datagramPacket);
            }
        } catch (Exception e) {throw new RuntimeException(e);}
        finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    @Override
    public void close() {
        if (in != null)
            in.close();
        if (out != null)
            out.close();
    }
}