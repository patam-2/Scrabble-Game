package ViewModel;

import Model.GameManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observable;
import java.util.Observer;


public class ViewModel implements Observer {

    public GameManager gameManager; // the Model
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

        this.numOfClients.set(String.valueOf(this.gameManager.host.numberOfClients));
        this.rounds.set(this.gameManager.host.rounds);
        System.out.println("update: " + numOfClients.getValue());
    }
}














//    @FXML
//    public ObservableMap<String, List<SimpleObjectProperty<Tile>>> map = FXCollections.observableHashMap();
