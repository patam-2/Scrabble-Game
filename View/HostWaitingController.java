package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;


public class HostWaitingController implements Observer
{
    @FXML
    public Button startGameButton;
    @FXML
    public Text numOfClients;

    public HostWaitingController() {
        HostController.viewModel.addObserver(this);
        System.out.println("hostWaiting Created");
    }

    @FXML
    public void handelStartGameButton(ActionEvent actionEvent) {
        Stage stage = (Stage)startGameButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("board.fxml")));
            primaryStage.setTitle("Scrabble Game Board");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    @FXML
    public void handleStartGameButtonPressed(MouseEvent event) {
        startGameButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    public void handleStartGameButtonReleased(MouseEvent event) {
        startGameButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @Override
    public void update(Observable o, Object arg) {
        numOfClients.textProperty().set(HostController.viewModel.numOfClients.get());
        System.out.println("the update: " + numOfClients.textProperty());
    }
}
