package View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ControllerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        MenuController menuController = loader.getController();

        primaryStage.setTitle("Scrabble Game Menu");
        primaryStage.setScene(new Scene(root, 650, 500));
        primaryStage.show();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Thread 1: Presses the "Host Player" button
        executorService.submit(() -> {
            try {
                Thread.sleep(1000); // Sleep for 1 second before pressing the button
                Platform.runLater(() -> menuController.handleHostPlayerButtonPressed(null));
                Thread.sleep(1000); // Sleep for 1 second after pressing the button
                Platform.runLater(() -> menuController.handleHostPlayerButtonReleased(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread 2: Presses the "Regular Player" button
        executorService.submit(() -> {
            try {
                Thread.sleep(2000); // Sleep for 2 seconds before pressing the button
                Platform.runLater(() -> menuController.handleRegularPlayerButtonPressed(null));
                Thread.sleep(1000); // Sleep for 1 second after pressing the button
                Platform.runLater(() -> menuController.handleRegularPlayerButtonReleased(null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
