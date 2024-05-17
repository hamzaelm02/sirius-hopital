package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.front.Design.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientPrincipalController implements Initializable {
    @FXML
    private BorderPane ClientBorderPane;

    @FXML
    private Button ShowClientButton;

    @FXML
    private AnchorPane leftClientPane;

    @FXML
    private Button returnClientButton;

    @FXML
    private AnchorPane rightClientPane;

    @FXML
    private AnchorPane topClientPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void ShowClientOnAction(ActionEvent actionEvent) {
        Stage showStage = new Stage();

        SwitchScene switchScene = new SwitchScene();
        AnchorPane pane = switchScene.getAnchorPane("src/main/resources/resources-fxml/ClientShowPane.fxml");
        Scene ShowScene = new Scene(pane);
        showStage.setHeight(500);
        showStage.setWidth(745);
        showStage.setTitle("LISTE DES MEDICAMENTS");
        showStage.setScene(ShowScene);
        showStage.show();

    }
    @FXML
    public void returnClientOnAction(ActionEvent actionEvent) {
        ClientBorderPane.getChildren().removeAll(topClientPane,rightClientPane,leftClientPane);
        BorderPane thirdPane = new SwitchScene().getBorderPane("src/main/resources/resources-fxml/MainFrameView.fxml");
        ClientBorderPane.setCenter(thirdPane);
    }
}
