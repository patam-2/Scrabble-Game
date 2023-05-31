package View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MenuController {


    @FXML
    private Button hostPlayerButton;

    @FXML
    private Button regularPlayerButton;

    @FXML
    private Button quitButton;

    @FXML
    private void handleHostPlayerButtonPressed(MouseEvent  event) {
        hostPlayerButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleRegularPlayerButtonPressed(MouseEvent  event) {
        regularPlayerButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleQuitButtonPressed(MouseEvent  event) {
        quitButton.setStyle("-fx-background-color: #FADBD8;");
    }

    @FXML
    private void handleHostPlayerButtonReleased(MouseEvent event) {
        hostPlayerButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    private void handleRegularPlayerButtonReleased(MouseEvent event) {
        regularPlayerButton.setStyle("-fx-background-color: #FFFFFF;");
    }

    @FXML
    private void handleQuitButtonReleased(MouseEvent event) {
        quitButton.setStyle("-fx-background-color: #FFFFFF;");
    }


//    public void openFile()
//    {
//        FileChooser fc=new FileChooser();
//        fc.setTitle("open maze file");
//        fc.setInitialDirectory(new File("./resources/pinkWall.jpg"));
//        File chosen=fc.showOpenDialog(null);
//        if(chosen!=null){
//            System.out.println(chosen.getName());
//        }
//    }

}
