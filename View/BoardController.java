package View;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class BoardController {

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
    public BoardDisplayer boardDisplayer;

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

                if (keyEvent.getCode() == KeyCode.UP) {
                    boardDisplayer.setRow(row - 1);
                }

                if (keyEvent.getCode() == KeyCode.LEFT)
                    boardDisplayer.setCol(col-1);

                if (keyEvent.getCode() == KeyCode.RIGHT)
                    boardDisplayer.setCol(col + 1);

                if (keyEvent.getCode() == KeyCode.DOWN)
                    boardDisplayer.setRow(row + 1);
            }
        });
    }
}
