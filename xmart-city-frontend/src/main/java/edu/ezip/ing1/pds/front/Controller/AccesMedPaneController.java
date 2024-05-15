package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Medicament;
import edu.ezip.ing1.pds.business.dto.Medicaments;
import edu.ezip.ing1.pds.business.dto.Profession;
import edu.ezip.ing1.pds.business.dto.Professions;
import edu.ezip.ing1.pds.client.MainSelectAllMedicament;
import edu.ezip.ing1.pds.client.SelectProfession;
import edu.ezip.ing1.pds.front.Design.SwitchScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URL;
import java.util.ResourceBundle;

public class AccesMedPaneController implements Initializable {
    @FXML
    public BorderPane accesMedPane;
    @FXML
    public Button connectionButton;
    @FXML
    private TableColumn<Medicament,Integer > categorieColumn;

    @FXML
    private TableColumn<Medicament, Integer> codeBarreColumn;

    @FXML
    private TableColumn<Medicament, Integer> idpColumn;

    @FXML
    private TableColumn<Medicament, String> nomColumn;

    @FXML private Label MedMessageError;
    @FXML
    public TextField idProfessionField;
    @FXML
    private TableView<Medicament> tableMed;

    public ObservableList<Medicament> medata = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MedMessageError.setVisible(false);



    }


    @FXML
    public void ConnectionButtonOnAction(ActionEvent actionEvent) throws  Exception {
        if (idProfessionField.getText().isEmpty()){
            MedMessageError.setText("Champs vides");
            MedMessageError.setTextFill(Color.RED);
            MedMessageError.setVisible(true);
        }else {

        Professions professions = SelectProfession.getProfession();
        for(final Profession profession : professions.getProfessions()){
            if(idProfessionField.getText().equals(profession.getId_profession().toString())){
                accesMedPane.getChildren().removeAll(MedMessageError,idProfessionField);
               accesMedPane.setCenter(new SwitchScene().getBorderPane("src/main/resources/resources-fxml/MedPaneShow.fxml"));

                Medicament medicament = new Medicament ();
                medicament.setId_profession(Integer.parseInt(idProfessionField.getText()));
                Medicaments medicaments = MainSelectAllMedicament.main(medicament);
                for(final  Medicament med : medicaments.getMedicaments()){
                medata.add(med);
                }
                codeBarreColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("code_barre"));
                nomColumn.setCellValueFactory(new PropertyValueFactory<Medicament,String>("nom"));
                categorieColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("categorie"));
                idpColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("id_profession"));


                tableMed.setItems(medata);





            }

        } }






    }
}
