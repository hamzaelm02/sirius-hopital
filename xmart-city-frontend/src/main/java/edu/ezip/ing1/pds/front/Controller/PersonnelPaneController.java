package edu.ezip.ing1.pds.front.Controller;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonnelPaneController implements Initializable {


    @FXML
    private TextField usernameField;
    @FXML
    private Button submitButton;
    @FXML
    private BorderPane PersonnelPane;
    @FXML
    private Label passwordFieldState;
    @FXML
    private PasswordField passwordField;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordFieldState.setVisible(false);
    }

  @FXML  public void PasswordFieldOnAction(KeyEvent actionEvent) {
        if (actionEvent.getCode().equals(KeyCode.ENTER) || actionEvent.getCode().equals(KeyCode.TAB)) {
            if (passwordField.getText().length() < 10) {
                passwordFieldState.setText("mot de passe comprenant un minimum de 10 caractères.");
                passwordFieldState.setVisible(true);
                passwordFieldState.setTextFill(Color.RED);
                passwordField.requestFocus();
                passwordField.pseudoClassStateChanged(PseudoClass.getPseudoClass("invalid"), true);

            } else {
                passwordFieldState.setText("Vous avez inséré un mot de passe valide.");

                passwordFieldState.setTextFill(Color.GREEN);
            }
        }
    }

    public void returnButtonOnAction(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/resources/resources-fxml/MainFrameView.fxml").toURI().toURL();
        BorderPane pane = FXMLLoader.load(url);
        PersonnelPane.setCenter(pane);
    }

   @FXML
    public void submitButtonOnAction(ActionEvent actionEvent) throws IOException {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            passwordFieldState.setText("Champs vide");
            passwordFieldState.setTextFill(Color.RED);
            passwordFieldState.setVisible(true);
        } else if (passwordField.getText().length() < 10){
            passwordField.setText("mot de passe comprenant un minimum de 10 caractères.");
            passwordFieldState.setVisible(true);
        } else {
            PersonnelPane.getChildren().removeAll();
            URL url = new File("src/main/resources/resources-fxml/PrincipalPaneView.fxml").toURI().toURL();
            BorderPane pane = FXMLLoader.load(url);
            PersonnelPane.setCenter(pane);
        }
    }
}
