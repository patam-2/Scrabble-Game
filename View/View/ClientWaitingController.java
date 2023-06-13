package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class ClientWaitingController {

    public static Stage clientWaitingStage;

    public ClientWaitingController() {
        clientWaitingStage = new Stage();
    }

    public void setClientWaitingStage(Stage currentClientStage) {
        clientWaitingStage = currentClientStage;
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
