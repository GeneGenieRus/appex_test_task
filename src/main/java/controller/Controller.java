package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import service.Service;

@org.springframework.stereotype.Controller
public class Controller {

    public Controller() {
    }

    @Autowired
    private Service service;


    public void create(ActionEvent actionEvent) {
    }



    @FXML
    private TableView<Note> noteList;

    @FXML
    private TableColumn<Note, Integer> idColumn;

    @FXML
    private TableColumn<Note, String> descriptionColumn;

    @FXML
    private TableColumn<Note, String> dateTimeColumn;

    @FXML
    private void initialize() {

        idColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("id"));

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("description"));
        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<Note, String>("dateTime"));

        noteList.setItems(FXCollections.observableArrayList(service.getAll()));
    }
}
