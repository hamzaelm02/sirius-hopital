package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Student;
import edu.ezip.ing1.pds.business.dto.Students;
import edu.ezip.ing1.pds.client.MainSelectClient;
import edu.ezip.ing1.pds.front.Design.SwitchScene;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalPaneController implements Initializable {

    @FXML
    public Label welcomeLabel;
    @FXML
    private BorderPane PrincipalBorderPane;
    @FXML
    private AnchorPane AccesMedPrincipalButton;
    @FXML
    private Button inscriptionPrincipalButton;
    @FXML
    private  AnchorPane topPane;
    @FXML
    private  AnchorPane leftPane;
    @FXML
    private AnchorPane MainShowPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void setIncriptionButtonOnAction(ActionEvent actionEvent) throws IOException {
        PrincipalBorderPane.getChildren().removeAll(welcomeLabel);
        SwitchScene switchScene = new SwitchScene();
        BorderPane pane = switchScene.getBorderPane("src/main/resources/resources-fxml/InscriptionPaneView.fxml");
        PrincipalBorderPane.setCenter(pane);
    }

    @FXML
    public void setAccesMedOnAction(ActionEvent mouseEvent) throws IOException {
       PrincipalBorderPane.getChildren().removeAll();


      SwitchScene switchScene = new SwitchScene();
        BorderPane pane = switchScene.getBorderPane("src/main/resources/resources-fxml/AccesMedPaneView.fxml");
        PrincipalBorderPane.setCenter(pane);

    }
    @FXML
    public void returnInscriptionButtonOnAction(ActionEvent actionEvent) throws IOException {
        PrincipalBorderPane.getChildren().removeAll(topPane, leftPane);
        SwitchScene switchScene = new SwitchScene();
        BorderPane pane = switchScene.getBorderPane("src/main/resources/resources-fxml/MainFrameView.fxml");
        PrincipalBorderPane.setCenter(pane);

    }


    @FXML
    public void ShowButtonOnAction(ActionEvent actionEvent) throws Exception{
        Stage showStage = new Stage();

        SwitchScene switchScene = new SwitchScene();
        AnchorPane pane = switchScene.getAnchorPane("src/main/resources/resources-fxml/ShowPaneView.fxml");
        Scene ShowScene = new Scene(pane);
        showStage.setHeight(500);
        showStage.setWidth(1070);
        showStage.setTitle("LISTE DES EMPLOYEES");
        showStage.setScene(ShowScene);
        showStage.show();
    }



}
