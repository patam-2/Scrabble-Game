package View;

import Model.Tile;
import ViewModel.ViewModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;


public class BoardController {

    public ViewModel viewModel;

    public ObservableMap<String, List<SimpleObjectProperty<Tile>>> playerTilesMap = FXCollections.observableHashMap();

    @FXML
    public BoardDisplayer boardDisplayer = new BoardDisplayer();

    @FXML
    private Button challengeButton;

    @FXML
    private Button queryButton;

    String[][] boardData = new String[][]{
                                            {"3W", "0", "0", "2L", "0", "0", "0", "3W", "0", "0", "0", "2L", "0", "0", "3W"},
                                            {"0", "2W", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "2W", "0"},
                                            {"0", "0", "2W", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2W", "0", "0"},
                                            {"2L", "0", "0", "2W", "0", "0", "0", "2L", "0", "0", "0", "2W", "0", "0", "2L"},
                                            {"0", "0", "0", "0", "2W", "0", "0", "0", "0", "0", "2W", "0", "0", "0", "0"},
                                            {"0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0"},
                                            {"0", "0", "2L", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2L", "0", "0"},
                                            {"3W", "0", "0", "2L", "0", "0", "0", "2WS", "0", "0", "0", "2L", "0", "0", "3W"},
                                            {"0", "0", "2L", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2L", "0", "0"},
                                            {"0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "3L", "0"},
                                            {"0", "0", "0", "0", "2W", "0", "0", "0", "0", "0", "2W", "0", "0", "0", "0"},
                                            {"2L", "0", "0", "2W", "0", "0", "0", "2L", "0", "0", "0", "2W", "0", "0", "2L"},
                                            {"0", "0", "2W", "0", "0", "0", "2L", "0", "2L", "0", "0", "0", "2W", "0", "0"},
                                            {"0", "2W", "0", "0", "0", "3L", "0", "0", "0", "3L", "0", "0", "0", "2W", "0"},
                                            {"3W", "0", "0", "2L", "0", "0", "0", "3W", "0", "0", "0", "2L", "0", "0", "3W"},};


    @FXML
    private void handleQueryButtonPressed(MouseEvent  event) {
        queryButton.setStyle("-fx-background-color: #E6B0AA;");
    }

    @FXML
    private void handleChallengeButtonPressed(MouseEvent  event) {
        challengeButton.setStyle("-fx-background-color: #E6B0AA;");
    }

    @FXML
    private void handleQueryButtonReleased(MouseEvent event) {
        queryButton.setStyle("-fx-background-color: #F2D7D5;");
    }

    @FXML
    private void handleChallengeButtonReleased(MouseEvent event) {
        challengeButton.setStyle("-fx-background-color: #F2D7D5;");
    }


    @FXML
    public void initialize()
    {
        boardDisplayer.setBoardMat(boardData);
        boardDisplayer.addEventFilter(MouseEvent.MOUSE_CLICKED, (e)->boardDisplayer.requestFocus());
        boardDisplayer.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int row = boardDisplayer.getRow();
                int col = boardDisplayer.getCol();

                if (keyEvent.getCode() == KeyCode.UP)
                    boardDisplayer.setRow(row - 1);

                if (keyEvent.getCode() == KeyCode.LEFT)
                    boardDisplayer.setCol(col - 1);

                if (keyEvent.getCode() == KeyCode.RIGHT)
                    boardDisplayer.setCol(col + 1);

                if (keyEvent.getCode() == KeyCode.DOWN)
                    boardDisplayer.setRow(row + 1);

                keyEvent.consume();
            }
        });
    }

    public void handelClick(ActionEvent e)
    {

    }
}
