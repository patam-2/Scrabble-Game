package ViewModel;

import Model.Client;
import javafx.beans.property.SimpleStringProperty;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;

public class ClientViewModel extends Observable implements Observer {

    public Client client;
    public SimpleStringProperty hasGameStarted;
    public SimpleStringProperty clientNumOfClients;
    public SimpleStringProperty clientName;

    public ClientViewModel(int port, InetAddress ip) {
        this.client = new Client(port, ip);
        this.clientName = new SimpleStringProperty();
        this.hasGameStarted = new SimpleStringProperty();
        this.clientNumOfClients = new SimpleStringProperty();
        client.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg != null && arg.toString().equals("s"))
        {
            hasGameStarted.setValue("start");
            System.out.println(hasGameStarted.getValue());
            System.out.println("CVM");
        }

        else if (arg != null && arg.toString().equals("t")) {
            int turn = Integer.parseInt(this.clientName.getValue());
            turn = 1 + (turn % Integer.parseInt(clientNumOfClients.getValue()));
            this.clientName = new SimpleStringProperty(String.valueOf(turn));
            System.out.println(this.clientName.getValue());
        }

        else if (arg != null && arg.toString().equals("p")) {
            clientNumOfClients.setValue(String.valueOf(Integer.parseInt(clientNumOfClients.getValue()) + 1));
        }

        else if (arg != null)
        {

        }
    }
}
