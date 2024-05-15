package edu.ezip.ing1.pds.front.Design;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class SwitchScene {

    private BorderPane borderPane;
    private AnchorPane  anchorPane;

    public BorderPane getBorderPane(String FxmlFileName){

        try {
            URL url = new File(FxmlFileName).toURI().toURL();
            if(FxmlFileName == null){
                throw new FileNotFoundException("Fxml file can't found");
            }
            borderPane = FXMLLoader.load(url);

        } catch (Exception e) {
           e.printStackTrace();
        }

        return  borderPane;

    }


   public  AnchorPane getAnchorPane(String FxmlFileName){
        try {
            URL url = new File(FxmlFileName).toURI().toURL();
            if(FxmlFileName == null){
                throw new FileNotFoundException("Fxml file can't found");
            }
            anchorPane = FXMLLoader.load(url);
        } catch (Exception e){
            e.printStackTrace();
        }
        return anchorPane;
   }


}
