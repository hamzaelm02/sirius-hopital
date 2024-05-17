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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AccesMedPaneController implements Initializable {
    @FXML
    public BorderPane accesMedPane;
    @FXML
    public Button connectionButton;

    @FXML private Label MedMessageError;
    @FXML
    public TextField idProfessionField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MedMessageError.setVisible(false);



    }



    @FXML
    public void ConnectionButtonOnAction(ActionEvent actionEvent) {
        MedMessageError.setVisible(false);

        if (idProfessionField.getText().isEmpty()) {
            MedMessageError.setText("Champs vides");
            MedMessageError.setTextFill(Color.RED);
            MedMessageError.setVisible(true);
            return;
        }

        try {
            Professions professions = SelectProfession.getProfession();
            boolean professionFound = false;

            for (Profession profession : professions.getProfessions()) {
                if (idProfessionField.getText().equals(profession.getId_profession().toString())) {
                    professionFound = true;
                    Stage showStage = new Stage();

                    SwitchScene switchScene = new SwitchScene();
                    AnchorPane pane = switchScene.getAnchorPane("src/main/resources/resources-fxml/M.fxml");
                    Scene ShowScene = new Scene(pane);
                    showStage.setHeight(500);
                    showStage.setWidth(745);
                    showStage.setTitle("LISTE DES MEDICAMENTS");
                    showStage.setScene(ShowScene);
                    showStage.show();
                    break;
                }
            }

            if (!professionFound) {
                MedMessageError.setText("Profession non trouvée");
                MedMessageError.setTextFill(Color.RED);
                MedMessageError.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MedMessageError.setText("Erreur de connexion");
            MedMessageError.setTextFill(Color.RED);
            MedMessageError.setVisible(true);
        }
    }




}
