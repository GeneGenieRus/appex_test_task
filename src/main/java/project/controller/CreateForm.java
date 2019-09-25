package project.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.Main;
import project.model.Note;
import project.service.Service;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
public class CreateForm implements Initializable {

    private final int MAX_LENGTH = 100;
    @Autowired
    private Service service;

    @Autowired
    private Controller controller;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField description;

    @FXML
    private TextField dateTime;

    public void Save(ActionEvent actionEvent) {
        Note note = new Note(description.getText(), LocalDateTime.parse(dateTime.getText(), Main.FORMATTER));
        service.save(note);
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
        controller.reloadTable();
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateTime.setText(LocalDateTime.now().format(Main.FORMATTER));

        description.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > MAX_LENGTH){
                    description.setText(oldValue);
                }
            }
        });
    }
}
