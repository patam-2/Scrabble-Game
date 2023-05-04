package test.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    public ClientHandler clientHandler;
    public int port;
    boolean stop;

    public MyServer(int port, ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        this.port = port;
    }

    public void start() {
        stop = false;
        new Thread(()->startServer()).start();
    }

    public void close()
    {
        stop = true;
        //new Thread(()->close());
    }

    private void startServer()
    {
        try {
            ServerSocket server = new ServerSocket(this.port);
            server.setSoTimeout(1000);

            while (!stop) {
                try {
                    Socket aClient = server.accept();
                    try {
                        clientHandler.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    finally {
                        aClient.close();
                        clientHandler.close();
                    }
                }
                catch (SocketTimeoutException e) {}

            }
            server.close();
        }catch (IOException e){e.printStackTrace();}
    }
}
