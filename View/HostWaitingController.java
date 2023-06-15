package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class HostWaitingController
{
    @FXML
    public Button startGameButton;
    @FXML
    public TextField numOfClients;

    public HostWaitingController(){
        this.numOfClients = new TextField("0");
        init();
    }

    public void init(){
        HostController.viewModel.numOfClients.bind(numOfClients.textProperty());
    }

    @FXML
    public void handelStartGameButton(ActionEvent actionEvent) {
        System.out.println("Waiting controller host: this is the num of clients: " + numOfClients.getText());
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

}
