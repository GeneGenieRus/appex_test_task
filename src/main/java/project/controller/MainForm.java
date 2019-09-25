package project.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;
import project.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import project.repository.NoteRepo;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class MainForm {

    public MainForm() {
    }

    @Autowired
    private NoteRepo noteRepo;


    public void create(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/createForm.fxml"));
        loader.setControllerFactory(Main.appCtx::getBean);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Create new note");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    public void reloadTable() {
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

        //wrap text by column https://stackoverflow.com/questions/22732013/javafx-tablecolumn-text-wrapping
        descriptionColumn.setCellFactory(tc -> {
            TableCell<Note, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(descriptionColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        noteList.setItems(FXCollections.observableArrayList(noteRepo.findAll()));
    }
}
