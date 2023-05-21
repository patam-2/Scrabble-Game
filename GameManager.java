package Model;

import java.net.InetAddress;

public class GameManager
{
    public Host host;

    public GameManager(InetAddress ip, int serverPort, int hostPort, int rounds) {
        this.host = Host.getHost(ip, serverPort, hostPort, rounds);
    }

    public void runGame() {
    }
}

