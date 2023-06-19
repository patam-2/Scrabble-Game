package Model;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;

public class GameManager extends Observable
{
    public Host host;
    public int serverPort = 1234;

    public GameManager(int hostPort, int rounds) {
        try {
            this.host = Host.getHost(InetAddress.getByName("localhost"), serverPort, hostPort, rounds);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void runGame() {
    }
}
