package View;

import Model.Client;
import ViewModel.ClientViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class ClientWaitingController {

    public static ClientViewModel clientViewModel;
    public Client client;
    @FXML
    public TextField clientNumOfClients;
    @FXML
    public TextField hasGameBinded;

    public ClientWaitingController() {

        this.hasGameBinded = new TextField("0");
        hasGameBinded.textProperty().addListener((o, ov, nv)->{
            System.out.println("has started: " + hasGameBinded.getText());
            if (hasGameBinded.getText() != null)
                handleClientStartGame();
        });
        this.clientNumOfClients = new TextField("0");
        clientViewModel = new ClientViewModel(ClientController.port, ClientController.ip);
        initialize();
    }

    public void initialize() {
        clientNumOfClients.textProperty().bindBidirectional(clientViewModel.clientNumOfClients);
        hasGameBinded.textProperty().bindBidirectional(clientViewModel.hasGameStarted);
    }

    @FXML
    public void handleClientStartGame() {
        System.out.println("has started 2: " + hasGameBinded.getText());
        try {
            System.out.println(clientNumOfClients.getText());
            Stage stage = (Stage) clientNumOfClients.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(ClientController.class.getResource("ClientBoard.fxml")));
            primaryStage.setTitle("Scrabble Game Board");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
