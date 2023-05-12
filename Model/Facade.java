package Model;

public class Facade
{
    public Board board;
    public Tile.Bag bag;
    public DictionaryManager dictionaryManager;
    public MyServer myServer;

    public Facade(int port, ClientHandler ch) {
        this.bag = Tile.Bag.getBag();
        this.board = Board.getBoard();
        this.dictionaryManager = new DictionaryManager();
        this.myServer = new MyServer(port, ch);
    }
}
