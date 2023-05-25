package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Host implements Player
{
    public int numberOfClients;
    public Socket hostSocket;
    public int serverPort;
    public Tile.Bag bag;
    public Board board;
    public InetAddress serverIp;
    public MyServer myClientServer;
    public static Host host = null;
    public HashMap<Integer, ArrayList<Tile>> playerTilesMap;
    public ArrayList<Tile> hostTiles;
    public int id;
    public int turn;
    public int rounds;


    private Host(InetAddress ip, int serverPort, int hostPort, int rounds) {
        this.id = 1;
        this.turn = 1;
        this.rounds = rounds;
        this.serverPort = serverPort;
        this.serverIp = ip;
        this.bag = Tile.Bag.getBag();
        this.board = Board.getBoard();
        this.hostTiles = new ArrayList<>();
        this.playerTilesMap = new HashMap<>();
        this.numberOfClients = 1;
        for (int i = 0; i < 26; i++) {
            this.hostTiles.add(bag.getRand());
        }
        this.playerTilesMap.put(id, this.hostTiles);
        this.myClientServer = new MyServer(hostPort, new HostClientHandler());
        this.myClientServer.start();
    }

    public static Host getHost(InetAddress ip, int serverPort, int hostPort, int rounds)
    {
        if (host == null) {
            host = new Host(ip, serverPort, hostPort, rounds);
        }
        return host;
    }

    @Override
    public void actionPlay(int type) {
    }

    public int placeWord(Word word) {
        try {
            hostSocket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int score = this.board.tryPlaceWord(word);

        if (score != 0) {
            for(int i = 0; i < word.tiles.length; i++) {
                this.hostTiles.remove(hostTiles.indexOf(word.tiles[i]));
            }
            for (int i = 0; i < word.tiles.length; i++) {
                Tile t = bag.getRand();
                this.hostTiles.add(t);
            }
            this.turn = 1 + (this.turn % this.numberOfClients);
        }
        try {
            hostSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return score;
    }

    public void closeGame() {
        this.myClientServer.close();
        System.out.println("GAME OVER!");
    }

    public boolean challenge(String s) {
        try {
            hostSocket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            PrintWriter outToServer = new PrintWriter(hostSocket.getOutputStream());
            String s1 = "";
            s1 += "C," +"newFile.txt," + s;
            outToServer.println(s1);
            outToServer.flush();
        } catch (IOException e) {throw new RuntimeException(e);}

        try {
            boolean flag = false;
            Scanner in = new Scanner(hostSocket.getInputStream());
            if (in.next().equals("true")) {
                flag = true;
            }
            hostSocket.close();
            return flag;
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
