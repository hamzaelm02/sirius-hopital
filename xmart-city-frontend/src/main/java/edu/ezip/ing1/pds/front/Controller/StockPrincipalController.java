package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Stock;
import edu.ezip.ing1.pds.client.SelectAllService;
import edu.ezip.ing1.pds.front.Design.SwitchScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;

public class StockPrincipalController implements Initializable {

    @FXML
    public AnchorPane leftPaneScan;
    @FXML
    public Button returnButtonScan;

    @FXML
    public BorderPane BorderPaneScan;
    @FXML
    public AnchorPane topPaneScan;
    @FXML
    public AnchorPane rightAnchorPane;


    @FXML
    private Button alertButton;

    @FXML
    private Button scanButton;

    @FXML
    private Button showButton;

    @FXML
    void alertButtonOnAction(ActionEvent event) throws SQLException, IOException, InterruptedException, URISyntaxException {

        Set<Stock> medicaments = SelectAllService.getAllMedic();
        new StockQuantityAlertPane(medicaments);

    }

    @FXML
    void scanButtonOnAction(ActionEvent event) {
        Stage showStage = new Stage();

        SwitchScene switchScene = new SwitchScene();
        AnchorPane ScanPane = switchScene.getAnchorPane("src/main/resources/resources-fxml/ScanStockPane.fxml");
        Scene ShowScene = new Scene(ScanPane);
        showStage.setHeight(500);
        showStage.setWidth(500);
        showStage.setTitle("Scanner");
        showStage.setScene(ShowScene);
        showStage.show();



    }

    @FXML
    void showButtonOnAction(ActionEvent event) {
        Stage showStage = new Stage();

        SwitchScene switchScene = new SwitchScene();
        AnchorPane pane = switchScene.getAnchorPane("src/main/resources/resources-fxml/StockShowFrame.fxml");
        Scene ShowScene = new Scene(pane);
        showStage.setHeight(500);
        showStage.setWidth(745);
        showStage.setTitle("LISTE DES MEDICAMENTS");
        showStage.setScene(ShowScene);
        showStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void returnButtonOnAction(ActionEvent actionEvent) {
    BorderPaneScan.getChildren().removeAll(leftPaneScan,rightAnchorPane,topPaneScan);
    BorderPane thirdPane = new SwitchScene().getBorderPane("src/main/resources/resources-fxml/MainFrameView.fxml");
    BorderPaneScan.setCenter(thirdPane);
    }
}
