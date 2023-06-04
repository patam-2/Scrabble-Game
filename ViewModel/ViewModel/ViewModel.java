package ViewModel;

import Model.Tile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class ViewModel implements Observer
{
    @FXML
    public ObservableMap<String, List<SimpleObjectProperty<Tile>>> map = FXCollections.observableHashMap();

    @Override
    public void update(Observable o, Object arg) {

    }
}
