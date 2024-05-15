package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.MainSelectClient;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;

public class ShowPaneController implements Initializable {
    @FXML
    public Button afficheButton;
    @FXML
    private AnchorPane MainShowPane;

    @FXML
    private TableView<Student> ShowTable;

    @FXML
    private TableColumn<Student, String> adresse;

    @FXML
    private TableColumn<Student, String> birthdate;

    @FXML
    private TableColumn<Student, String> email;

    @FXML
    private TableColumn<Student, Integer> ide;

    @FXML
    private TableColumn<Student, Integer> idp;

    @FXML
    private TableColumn<Student, String> nom;

    @FXML
    private TableColumn<Student, String> prenom;

    @FXML
    private TableColumn<Student, String> startingdate;

    @FXML
    private TableColumn<Student, Double> taille;

    protected ObservableList<Student> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.add(new Student(null, "nom", "prenom", "num Rue/Avenue nom ","email@xyz.xyz","mm-dd-yyyy ",null,"mm-dd-yyyy ", null ));

       ide.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id_employee"));
        nom.setCellValueFactory(new PropertyValueFactory<Student,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Student,String>("prenom"));
        adresse.setCellValueFactory(new PropertyValueFactory<Student,String>("adresse"));
        email.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
        birthdate.setCellValueFactory(new PropertyValueFactory<Student,String>("birthdate"));
        taille.setCellValueFactory(new PropertyValueFactory<Student,Double>("taille"));
        startingdate.setCellValueFactory(new PropertyValueFactory<Student,String>("startingdate"));
        idp.setCellValueFactory(new PropertyValueFactory<Student,Integer>("id_profession"));
        ShowTable.setItems(data);
    }
    @FXML
    public void afficheButtonOnAction(ActionEvent actionEvent) {

        try {
            Students students = MainSelectClient.main();
            for(final Student student : students.getStudents()){
                data.add(new Student(student.getId_employee(), student.getNom(), student.getPrenom(), student.getAdresse(), student.getEmail(), student.getBirthdate(), student.getTaille(), student.getStartingdate(),student.getId_profession()));
            }
            ShowTable.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
