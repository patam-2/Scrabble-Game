package View;

import Model.GameManager;
import ViewModel.ViewModel;
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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;


public class HostController
{
    @FXML
    public TextField hostPort;
    @FXML
    public TextField hostRounds;
    @FXML
    public Button hostSubmitButton;
    @FXML
    public Button hostReturnButton;
    public int rounds;
    public int port;
    public static ViewModel viewModel;

    public HostController() {
    }

    public void initialize() {
        hostSubmitButton.setDisable(true);                                               // Disable the submit button initially
        hostPort.textProperty().addListener((observable, oldValue, newValue) -> {        // Add a listener to both text fields' textProperty
            if (!isNumeric(hostPort.getText()) || !isNumeric(hostRounds.getText())) {    // Check if either text field contains letters or is not a valid number
                hostSubmitButton.setDisable(true);
            } else {
                hostSubmitButton.setDisable(false);
            }
        });

        hostRounds.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumeric(hostPort.getText()) || !isNumeric(hostRounds.getText())) {     // Check if either text field contains letters or is not a valid number
                hostSubmitButton.setDisable(true);
            } else {
                hostSubmitButton.setDisable(false);
            }
        });
    }


    public boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

    public boolean isValidIPAddress(String input) {
        try {
            InetAddress.getByName(input);
            return true;
        } catch (UnknownHostException e) {return false;}
    }

    @FXML
    public void hostHandleSubmit(ActionEvent event) {
        try {
            port = Integer.parseInt(hostPort.getText());
            rounds = Integer.parseInt(hostRounds.getText());
            System.out.println("Host Port: " + port);
            System.out.println("Rounds: " + rounds);
            viewModel = new ViewModel(new GameManager(port, rounds));
            Stage stage = (Stage)hostSubmitButton.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hostWaiting.fxml")));
            primaryStage.setTitle("Waiting Lounge");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (Exception e) {e.printStackTrace();}
    }

    @FXML
    public void hostHandelBackButton() {
        Stage stage = (Stage) hostReturnButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu.fxml")));
            primaryStage.setTitle("Scrabble Game Menu");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    @FXML
    public void handleHostSubmitButtonPressed(MouseEvent event) {
        hostSubmitButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    public void handleHostSubmitButtonReleased(MouseEvent event) {
        hostSubmitButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    public void handleHostReturnButtonPressed(MouseEvent  event) {
        hostReturnButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    public void handleHostReturnButtonReleased(MouseEvent event) {
        hostReturnButton.setStyle("-fx-background-color: #FFFFFF;");
    }
}
