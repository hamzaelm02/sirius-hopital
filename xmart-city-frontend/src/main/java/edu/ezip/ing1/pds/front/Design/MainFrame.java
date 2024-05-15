package edu.ezip.ing1.pds.front.Design;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class MainFrame extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {
        URL url = new File("src/main/resources/resources-fxml/MainFrameView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene =  new Scene(root);
        PrimaryStage.setScene(scene);
        PrimaryStage.setTitle("EPITAL");
        PrimaryStage.show();
    }
}
