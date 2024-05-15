package edu.ezip.ing1.pds.front.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.ezip.ing1.pds.business.dto.Profession;
import edu.ezip.ing1.pds.business.dto.Professions;
import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.InsertStudentsClientRequest;
import edu.ezip.ing1.pds.client.MainInsertClient;
import edu.ezip.ing1.pds.client.MainSelectAllProfession;
import edu.ezip.ing1.pds.client.MainUpdateClient;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezipe.ing1.pds.client.MainDeleteEmployee;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;
import java.util.UUID;

public class InscriptionPaneController implements Initializable {


    @FXML
    public Button AjouterButton;
    @FXML
    public TextField ideField;
    @FXML private Label SuppMessageError;
    @FXML private TextField idProfField;
    @FXML
    private  Label InscriptionPaneMessageError;
    @FXML private TextField NomField;
    @FXML private TextField PrenomField;
    @FXML private TextField AdresseField;
    @FXML private TextField EmailField;
    @FXML private TextField TailleField;
    @FXML private ComboBox<String> ProfField;
    @FXML private DatePicker bpicker;
    @FXML private DatePicker spicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProfField.setItems(FXCollections.observableArrayList("unknown", "medecin generaliste", "infermier", "employe de stock", "medecin chirugien"));
        InscriptionPaneMessageError.setVisible(false);
        SuppMessageError.setVisible(false);

    }
    @FXML public void AjouterButtonOnAction(ActionEvent actionEvent){
        if(NomField.getText().isEmpty() || PrenomField.getText().isEmpty() || AdresseField.getText().isEmpty() || EmailField.getText().isEmpty() || TailleField.getText().isEmpty() ){
            InscriptionPaneMessageError.setText("Champs vides");
            InscriptionPaneMessageError.setTextFill(Color.RED);
            InscriptionPaneMessageError.setVisible(true);

        } else {
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Ajouter avec succes !!");

            String nom = NomField.getText();
            String prenom = PrenomField.getText();
            String adresse = AdresseField.getText();
            String email = EmailField.getText();
            String birthdate = getDatePickerValueTime(bpicker.getValue());
            Double taille = Double.parseDouble(TailleField.getText());
            String startingdate = getDatePickerValueTime(spicker.getValue());
            String nameP = ProfField.getValue().toString();

            Profession profession = new Profession();
            profession.setNom_profession(nameP);


            Students students = new Students();
            Student student = new Student();
            student.setNom(nom);
            student.setPrenom(prenom);
            student.setAdresse(adresse);
            student.setEmail(email);
            student.setBirthdate(birthdate);
            student.setTaille(taille);
            student.setStartingdate(startingdate);
            student.setId_profession(MainSelectAllProfession.getIdProfession(profession));
            students.add(student);

            MainInsertClient.addEmployee(students);

        } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
   @FXML public void SupprimerButtonOnAction(ActionEvent actionEvent) throws Exception {
        if(idProfField.getText().isEmpty()){
            SuppMessageError.setText("Champs vides");
            SuppMessageError.setTextFill(Color.RED);
            SuppMessageError.setVisible(true);

        } else  {
            Students students = new Students();
            Student student = new Student();

           String idprof = idProfField.getText();
           student.setId_employee(Integer.parseInt(idprof));
           students.add(student);
            MainDeleteEmployee.DeleteEmployee(students);



        }
   }

    public String getDatePickerValueTime(LocalDate selectedDate){
        LocalDateTime selectedDateTime = selectedDate.atStartOfDay();
       String date = selectedDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return  date;
    }


    @FXML
    public void UpdateButtonOnAction(ActionEvent actionEvent) throws Exception {
        Student student = new Student();
        student.setId_employee(Integer.parseInt(ideField.getText()));
        System.out.println("voici mon employée : " + student);
        student.setEmail(EmailField.getText());
        student.setAdresse(AdresseField.getText());
        Students students = new Students();
        students.add(student);
        MainUpdateClient.UpdateEmployee(students);

    }
}
