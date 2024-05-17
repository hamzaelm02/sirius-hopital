package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Medicament;
import edu.ezip.ing1.pds.business.dto.Medicaments;
import edu.ezip.ing1.pds.business.dto.Profession;
import edu.ezip.ing1.pds.business.dto.Professions;
import edu.ezip.ing1.pds.client.MainSelectAllMedicament;
import edu.ezip.ing1.pds.client.SelectProfession;
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

import java.net.URL;
import java.util.ResourceBundle;

public class MedPaneShowController implements Initializable {
    @FXML
    private Button MedShowButton;

    @FXML
    private TableColumn<Medicament, Integer> categorieColumn;

    @FXML
    private TableColumn<Medicament, Integer> codeBarreColumn;

    @FXML
    private TableColumn<Medicament, Integer> idpColumn;

    @FXML
    private TableColumn<Medicament, String> nomColumn;

    @FXML
    private TableView<Medicament> tableMed;

    ObservableList<Medicament> medata = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
    }
    private void setupTableColumns() {
        medata.add(new Medicament(null,"unknown",null,null));
        codeBarreColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("code_barre"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Medicament,String>("nom"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("categorie"));
        idpColumn.setCellValueFactory(new PropertyValueFactory<Medicament,Integer>("id_profession"));
        tableMed.setItems(medata);

    }

    @FXML
    void MedShowOnAction(ActionEvent event) {
        AccesMedPaneController accesMedPaneController = new AccesMedPaneController();
        try {
            Professions professions = SelectProfession.getProfession();
            for (final Profession profession : professions.getProfessions()){
                if(accesMedPaneController.idProfessionField.getText().equals(profession.getId_profession().toString())){
                    loadMedicamentData(Integer.parseInt(accesMedPaneController.idProfessionField.getText()));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("Erreur de connexion");
            alert.showAndWait();
        }

    }

     private void loadMedicamentData(int idProfession) throws Exception {
        Medicament medicament = new Medicament();
        medicament.setId_profession(idProfession);
        Medicaments medicaments = MainSelectAllMedicament.main(medicament);

        medata.clear();
        medata.addAll(medicaments.getMedicaments());

        tableMed.setItems(medata);
    }

}
