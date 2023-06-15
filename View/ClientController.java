package View;

import Model.Client;
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

public class ClientController
{
    @FXML
    public TextField clientIP;
    @FXML
    public TextField clientPort;
    @FXML
    public Button clientSubmitButton;
    @FXML
    public Button clientReturnButton;
    public InetAddress ip;
    public int port;
    public Client client;

    public ClientController() {
    }

    public void initialize() {
        clientSubmitButton.setDisable(true);                                                   // Disable the submit button initially
        clientPort.textProperty().addListener((observable, oldValue, newValue) -> {            // Add a listener to both text fields' textProperty
            if (!isNumeric(clientPort.getText()) || !isValidIPAddress(clientIP.getText())) {   // Check if either text field contains letters or is not a valid number
                clientSubmitButton.setDisable(true);
            } else {
                clientSubmitButton.setDisable(false);
            }
        });

        clientIP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isNumeric(clientPort.getText()) || !isValidIPAddress(clientIP.getText())) {    // Check if either text field contains letters or is not a valid number
                clientSubmitButton.setDisable(true);
            } else {
                clientSubmitButton.setDisable(false);
            }
        });
    }

    private boolean isNumeric(String input) {
        return input.matches("\\d+");
    }

    private boolean isValidIPAddress(String input) {
        try {
            InetAddress.getByName(input);
            return true;
        } catch (UnknownHostException e) {return false;}
    }

    @FXML
    private void clientHandleSubmitPressed(ActionEvent event) {
        try {
            ip = InetAddress.getByName(clientIP.getText());
            port = Integer.parseInt(clientPort.getText());
            System.out.println("Client Ip: " + ip);
            System.out.println("Client Port: " + port);
            client = new Client(port, ip);

            Stage stage = (Stage)clientSubmitButton.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clientWaiting.fxml")));
            primaryStage.setTitle("Waiting Lounge");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (Exception e) {e.printStackTrace();}
    }

    @FXML
    private void clientHandelBackButton()
    {
        Stage stage = (Stage)clientReturnButton.getScene().getWindow();
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
    private void handleClientSubmitButtonPressed(MouseEvent event) {
        clientSubmitButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleClientSubmitButtonReleased(MouseEvent event) {
        clientSubmitButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    private void handleClientReturnButtonPressed(MouseEvent  event) {
        clientReturnButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleClientReturnButtonReleased(MouseEvent event) {
        clientReturnButton.setStyle("-fx-background-color: #FFFFFF;");
    }
}
