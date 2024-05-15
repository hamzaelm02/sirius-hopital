package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Stock;
import edu.ezip.ing1.pds.business.dto.Stocks;
import edu.ezip.ing1.pds.client.MainSelectAllStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class StockShowController implements Initializable {

    @FXML
    private TableColumn<Stock, String> datexpColumn;

    @FXML
    private TableColumn<Stock, Integer> mIDcolumn;

    @FXML
    private TableColumn<Stock, String> nomColumn;

    @FXML
    private TableColumn<Stock, Integer> quantityColumn;

    @FXML
    private TableColumn<Stock, Integer> seuilColumn;

    @FXML
    private TableColumn<Stock, String> seuildateColumn;

    @FXML
    private TableView<Stock> stockTab;

    protected  ObservableList<Stock> stockdata = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stockdata.add(new Stock(null, null, "jj/mm/AAAA", null, "unknown","jj/mm/AAAA"));

        mIDcolumn.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("medicament_id"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("quantite"));
        datexpColumn.setCellValueFactory(new PropertyValueFactory<Stock,String>("date_expiration"));
        seuilColumn.setCellValueFactory(new PropertyValueFactory<Stock,Integer>("seuil"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Stock,String>("nom"));
        seuildateColumn.setCellValueFactory(new PropertyValueFactory<Stock,String>("seuil_date"));

        stockTab.setItems(stockdata);
    }

    @FXML
    void showPaneButtonOnAction(ActionEvent event) {

        try {
            Stocks stocks = MainSelectAllStock.main();
            for(final Stock stock : stocks.getStudents()){
                stockdata.add(new Stock(stock.getMedicament_id(),stock.getQuantite(),stock.getDate_expiration(),stock.getSeuil(),stock.getNom(),stock.getSeuil_date()));
            }
           stockTab.setItems(stockdata);
        } catch (Exception e) {

        }

    }
}
