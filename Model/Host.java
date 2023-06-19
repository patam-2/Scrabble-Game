package Model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;


public class Host extends Observable implements Player {

    public int numberOfClients = 0;
    public Socket hostSocket;
    public int serverPort;
    public Tile.Bag bag;
    public Board board;
    public InetAddress serverIp;
    public MyServer myClientServer;
    public FacadeServer facadeServer;
    public static Host host = null;
    public HashMap<Integer, ArrayList<Tile>> playerTilesMap;
    public int id;
    public int turn;
    public int rounds = 0;


    private Host(InetAddress ip, int serverPort, int hostPort, int rounds) {

        this.id = 1;
        this.turn = 1;
        this.rounds = rounds;
        this.serverPort = serverPort;
        this.serverIp = ip;
        this.bag = Tile.Bag.getBag();
        this.board = Board.getBoard();
        this.playerTilesMap = new HashMap<>();
        this.numberOfClients = 1;
        this.playerTilesMap.put(id , new ArrayList<Tile>());
        for (int i = 0; i < 7; i++) {
            this.playerTilesMap.get(id).add(bag.getRand());
        }
        this.myClientServer = new MyServer(hostPort, new HostClientHandler());
        this.myClientServer.start();
        //this.facadeServer = new FacadeServer(serverPort);
    }

    public static Host getHost(InetAddress ip, int serverPort, int hostPort, int rounds)
    {
        if (host == null) {
            host = new Host(ip, serverPort, hostPort, rounds);
        }
        return host;
    }

    @Override
    public void actionPlay(int type) {}
    public void setNumberOfRounds(int rounds) {
        this.rounds = rounds;
    }
    public int getNumberOfRounds()
    {
        return this.rounds;
    }

    public int placeWord(Word word) {
        int score = this.board.tryPlaceWord(word);

        if (score != 0) {
            for(int i = 0; i < word.tiles.length; i++) {
                this.playerTilesMap.get(id).remove(playerTilesMap.get(1).indexOf(word.tiles[i]));
            }
            for (int i = 0; i < word.tiles.length; i++) {
                Tile t = bag.getRand();
                this.playerTilesMap.get(id).add(t);
            }
            this.turn = 1 + (this.turn % this.numberOfClients);
            //updateAndNotify();
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
            s1 += "C," + "alice_in_wonderland.txt,HarryPotter.txt,TheMatrix.txt,newFile.txt," + s;
            outToServer.println(s1);
            outToServer.flush();
        } catch (IOException e) {throw new RuntimeException(e);}

        try {
            boolean flag = false;
            Scanner in = new Scanner(hostSocket.getInputStream());
            if (in.next().equals("true")) {
                flag = true;
            }
            return flag;
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    public void incrementNumberOfClients() {
        this.numberOfClients++;
        updateAndNotify();
        System.out.println("Host's Number Of Clients: " + this.numberOfClients);
    }

    public void updateAndNotify() {
        setChanged();
        notifyObservers();
    }
}