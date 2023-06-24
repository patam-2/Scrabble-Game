package View;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

        import java.io.IOException;

public class ClientBoardApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.BoardApplication.class.getResource("ClientBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 500);
        stage.setTitle("Scrabble Game Board");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
