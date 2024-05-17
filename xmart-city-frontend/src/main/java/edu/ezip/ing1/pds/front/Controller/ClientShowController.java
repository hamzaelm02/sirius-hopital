package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Gestion;
import edu.ezip.ing1.pds.business.dto.Gestions;
import edu.ezip.ing1.pds.client.MainSelectGestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientShowController implements Initializable {
    @FXML
    private TableColumn<Gestion, String> desColumn;

    @FXML
    private TableColumn<Gestion, String> effetColumn;

    @FXML
    private TableColumn<Gestion, Integer> idsimColumn;

    @FXML
    private TableColumn<Gestion, Integer> medidColumn;

    @FXML
    private TableColumn<Gestion, String> nomColumn;

    @FXML
    private Button showButton;
    @FXML
    private TableView<Gestion> medClientTab;
    ObservableList<Gestion> mdata = FXCollections.observableArrayList();

    @FXML
    void showOnAction(ActionEvent event) throws Exception {
        Gestions gestions = MainSelectGestion.getGestion();
        for(final Gestion gestion : gestions.getStudents()){
            mdata.add(gestion);
        }
        medClientTab.setItems(mdata);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mdata.add(new Gestion(null,"unknown","unknown","unknown",null));
        medidColumn.setCellValueFactory(new PropertyValueFactory<Gestion,Integer>("medicament_id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Gestion,String>("nom_medicament"));
        desColumn.setCellValueFactory(new PropertyValueFactory<Gestion,String>("description"));
        effetColumn.setCellValueFactory(new PropertyValueFactory<Gestion,String>("effet_secondaire"));
        idsimColumn.setCellValueFactory(new PropertyValueFactory<Gestion,Integer>("id_similaire"));
        medClientTab.setItems(mdata);

    }
}
