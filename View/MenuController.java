package View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class MenuController {

    @FXML
    private Button hostPlayerButton;

    @FXML
    private Button regularPlayerButton;

    @FXML
    private Button quitButton;

    @FXML
    public void handleHostPlayerButtonPressed(MouseEvent event) {
        hostPlayerButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    public void handleRegularPlayerButtonPressed(MouseEvent event) {
        regularPlayerButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleQuitButtonPressed(MouseEvent  event) {
        quitButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    public void handleHostPlayerButtonReleased(MouseEvent event) {
        hostPlayerButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    public void handleRegularPlayerButtonReleased(MouseEvent event) {
        regularPlayerButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    private void handleQuitButtonReleased(MouseEvent event) {
        quitButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    public void createHostPlayer()
    {
        Stage stage = (Stage)hostPlayerButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hostPage.fxml")));
            primaryStage.setTitle("Host Login");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }

    @FXML
    public void createClientPlayer()
    {
        Stage stage = (Stage)regularPlayerButton.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clientPage.fxml")));
            primaryStage.setTitle("Client Login");
            primaryStage.setScene(new Scene(root, 650, 500));
            primaryStage.show();
        } catch (IOException e) {throw new RuntimeException(e);}
    }
}
