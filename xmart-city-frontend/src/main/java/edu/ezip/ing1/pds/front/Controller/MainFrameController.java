package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.front.Design.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.File;

public class MainFrameController implements Initializable {

    @FXML
    public Button stockButton;
    @FXML
    public AnchorPane centerPane;
    @FXML
    private AnchorPane topPane;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void personnelButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        borderPane.getChildren().removeAll(topPane);
       SwitchScene switchScene = new SwitchScene();
       BorderPane pane = switchScene.getBorderPane("src/main/resources/resources-fxml/PersonnelPane.fxml");
       borderPane.setCenter(pane);
    }

    @FXML
    public void stockButtonOnAction(ActionEvent actionEvent) {
        borderPane.getChildren().removeAll(centerPane, topPane);
        BorderPane secondPane = new SwitchScene().getBorderPane("src/main/resources/resources-fxml/StockPrincipalPane.fxml");
        borderPane.setCenter(secondPane);

    }
}
