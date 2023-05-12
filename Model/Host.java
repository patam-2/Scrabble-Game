package Model;

import java.util.ArrayList;


public class Host implements Player
{
    public int port; // should it be permanent (final)?
    public ArrayList<Player> playersQueue;
    private ClientHandler clientHandler;
    private Facade facade;
    public static Host host;

    private Host() {
        this.clientHandler = new HostClientHandler();
        playersQueue.add(this);
    }

    public static Host getHost(int port) {
        if (host == null) {
            host = new Host();
            host.facade = new Facade(port, host.clientHandler);
            host.port = port;
        }
        else {
            System.out.println("There is already sa Host!:)");
        }
        return host;
    }

    public int placeWord(Word word) {
        return this.facade.board.tryPlaceWord(word);
    }

    public boolean challenge(String s) {
        return this.facade.dictionaryManager.challenge(s);
    }

    public int[] getTiles(){
        return this.facade.bag.getQuantities();
    }

    public int getClientsNumber(){
        return playersQueue.size();
    }

    public boolean addClients(Client c) {
        if (playersQueue.size() < 4)
            return playersQueue.add(c);
        return false;
    }

    public boolean removeClient(Client c) {
        return playersQueue.remove(c);
    }

    public void startGame(int rounds){

        if (playersQueue.size() > 1){

            for (int i = 0; i < rounds; i++)
            {
                //playersQueue.get(i%playerQueue.size()).action(); // specific player
            }

            System.out.println("GAME OVER!!!");
        }

        else
            System.out.println("not enough players"); // maybe wanting for more players (busywating)



    }
}
