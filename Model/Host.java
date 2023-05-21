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
    public HashMap<Integer, ArrayList<Character>> playerTilesMap;
    public ArrayList<Character> hostTiles;
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
        for (int i = 0; i < 26; i++) {
            this.hostTiles.add(bag.getRand().letter);
        }
        this.playerTilesMap.put(id, this.hostTiles);
        this.myClientServer = new MyServer(hostPort, new HostClientHandler());
        try {
            this.hostSocket = new Socket(serverIp, serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Host getHost(InetAddress ip, int serverPort, int hostPort, int rounds)
    {
        if (host == null) {
            return new Host(ip, serverPort, hostPort, rounds);
        }
        return host;
    }

    @Override
    public void actionPlay(int type) {
    }

    public int placeWord(Word word) {
        int score = this.board.tryPlaceWord(word);

        for(int i = 0; i < word.tiles.length; i++) {
            this.hostTiles.remove(word.tiles[i].letter);
        }

        if (score != 0) {
            for (int i = 0; i < word.tiles.length; i++) {
                Tile t = bag.getRand();
                this.hostTiles.add(t.letter);
            }
            this.turn = (this.turn + 1) % this.numberOfClients;
        }
        return score;
    }

    public void closeGame() {
        this.myClientServer.close();
        try {
            this.hostSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("GAME OVER!");
    }

    public boolean challenge(String s) {
        try {
            PrintWriter outToServer = new PrintWriter(hostSocket.getOutputStream());
            String s1 = "";
            s1 += "C," + s;
            outToServer.println(s1);
        } catch (IOException e) {throw new RuntimeException(e);}

        try {
            Scanner in = new Scanner(hostSocket.getInputStream());
            String input = in.next();
            if (input.equals("true\n"))
                return true;
            return false;
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
