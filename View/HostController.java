package View;

import Model.GameManager;
import ViewModel.ViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class HostController implements Initializable {

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
        this.hostPort = new TextField("0000");
        this.hostRounds = new TextField("0");
        viewModel = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

    @FXML
    public void hostHandleSubmit(ActionEvent event) {
        try {
            port = Integer.parseInt(hostPort.getText());
            rounds = Integer.parseInt(hostRounds.getText());
            System.out.println();
            System.out.println("Host's Port: " + port);
            System.out.println("Number Of Rounds: " + rounds);
            viewModel = new ViewModel(new GameManager(port, rounds));
            viewModel.numOfClients.set("1");
            Stage stage = (Stage)hostSubmitButton.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(Objects.requireNonNull(getClass().getResource("hostWaiting.fxml")));
            primaryStage.setTitle("Waiting Lounge");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
            HostWaitingController hostWaitingController = fxmlLoader.getController();
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
