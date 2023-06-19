package ViewModel;

import Model.GameManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observable;
import java.util.Observer;


public class ViewModel implements Observer {

    public GameManager gameManager;      // The Model
    public SimpleIntegerProperty rounds;
    public SimpleStringProperty hostPort;
    public SimpleStringProperty numOfClients;

    public ViewModel(GameManager gameManagerModel) {
        this.numOfClients = new SimpleStringProperty();
        this.hostPort = new SimpleStringProperty();
        this.rounds = new SimpleIntegerProperty();
        this.gameManager = gameManagerModel;
        this.gameManager.host.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        int num = this.gameManager.host.numberOfClients;
        int numOfRounds = this.gameManager.host.rounds;
        this.numOfClients.bindBidirectional(new SimpleStringProperty(String.valueOf(num)));
        this.rounds.bindBidirectional(new SimpleIntegerProperty(numOfRounds));
        System.out.println("The Update In The ViewModel- Number Of Clients: " + numOfClients.getValue());
    }
}














//    @FXML
//    public ObservableMap<String, List<SimpleObjectProperty<Tile>>> map = FXCollections.observableHashMap();