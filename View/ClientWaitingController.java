package View;

import Model.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class ClientWaitingController {

    public static Stage clientWaitingStage;

    @FXML
    public TextField clientNumOfClients;

    // OF TRY 1
    //private HostController hostController = HostController.getInstance();

    public ClientWaitingController() {
        this.clientNumOfClients = new TextField("0");
        clientWaitingStage = new Stage();
        Client client = new Client(ClientController.port, ClientController.ip);
        initialize();
    }

    public void initialize() {

        // WHEN SHOWING TZVIKA
        //clientNumOfClients.textProperty().bindBidirectional(HostController.viewModel.numOfClients);

        // TRY 1
        //clientNumOfClients.textProperty().bindBidirectional(hostController.viewModel.numOfClients);

        // TRY 2
        // ViewModel viewModel = HostController.getInstance().viewModel;
        // clientNumOfClients.textProperty().bindBidirectional(viewModel.numOfClients);
    }



    @FXML
    public static void handleClientStartGame() {
        try {
            Stage stage = (Stage)clientWaitingStage.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(ClientController.class.getResource("board.fxml")));
            primaryStage.setTitle("Scrabble Game Board");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
