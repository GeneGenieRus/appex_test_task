package project.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;
import project.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import project.service.Service;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    public Controller() {
    }

    @Autowired
    private Service service;


    public void create(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createForm.fxml"));
        loader.setControllerFactory(Main.appCtx::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 600);
        Stage stage = new Stage();
        stage.setTitle("Create new note");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public void reloadTable(){
        initialize();
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
        dateTimeColumn.setCellValueFactory(cellData -> {
            SimpleStringProperty simpleStringProperty = new SimpleStringProperty();
            simpleStringProperty.setValue(cellData.getValue().getDateTime().format(Main.FORMATTER));
            return simpleStringProperty;
        });

        noteList.setItems(FXCollections.observableArrayList(service.getAll()));
    }
}