package edu.ezip.ing1.pds.front.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.Stock;
import edu.ezip.ing1.pds.client.SelectAllService;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.ConfigLoader;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class ScanStockController implements Initializable {

    @FXML
    private TextField IDMField;

    @FXML
    private TextField QField;

    @FXML
    private Button enterScanButton;

    @FXML
    private ComboBox<String> typeBox;

    @FXML
    void enterScanButtonOnAction(ActionEvent event) {
        try {
            int medicamentId = Integer.parseInt(IDMField.getText());
            int quantity = Integer.parseInt(QField.getText());
            String operation = typeBox.getValue();

            // Ajoutez ces lignes pour déboguer
            System.out.println("ID Médicament: " + medicamentId);
            System.out.println("Quantité: " + quantity);
            System.out.println("Opération: " + operation);

            updateStock(medicamentId, quantity, operation);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'opération a été enregistrée avec succès!");
            alert.showAndWait();

        } catch (NumberFormatException e) {
            showErrorAlert("Veuillez entrer des valeurs numériques valides pour l'ID et la quantité.");
        } catch (SQLException | IOException | InterruptedException e) {
            showErrorAlert("Une erreur est survenue lors de la mise à jour du stock.");
        }
    }






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            typeBox.getItems().addAll("Entrée","Sortie");
            typeBox.setValue("Entrée");
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateStock(int medicamentId, int quantity, String operation) throws SQLException, IOException, InterruptedException {
        Set<Stock> allMedicaments = SelectAllService.getAllMedic();

        for (Stock stock : allMedicaments) {
            if (stock.getMedicament_id() == medicamentId) {
                int newQuantity = operation.equals("Entrée") ? stock.getQuantite() + quantity : stock.getQuantite() - quantity;

                // Journalisation pour le débogage
                System.out.println("ID Médicament: " + medicamentId);
                System.out.println("Nouvelle Quantité: " + newQuantity);

                // Créer la requête pour mettre à jour le stock dans la base de données
                int birthdate = 0;
                final NetworkConfig networkConfig = ConfigLoader.loadConfig(NetworkConfig.class, "network.yaml");
                final ObjectMapper objectMapper = new ObjectMapper();
                final String requestId = UUID.randomUUID().toString();
                final Request request = new Request();
                request.setRequestId(requestId);
                request.setRequestOrder("UPDATE_MEDICAMENT");

                Map<String, Object> requestBody = new LinkedHashMap<>();
                requestBody.put("medicament_id", medicamentId);
                requestBody.put("quantite", newQuantity);

                JsonNode requestBodyJson = objectMapper.valueToTree(requestBody);
                request.setRequestBody(requestBodyJson);

                final byte[] requestBytes = objectMapper.writeValueAsBytes(Collections.singletonMap("request", request));

                final ClientRequest clientRequest = new ClientRequest(networkConfig, birthdate++, request, null, requestBytes) {
                    @Override
                    public Object readResult(String body) throws IOException {
                        // Journalisation de la réponse pour le débogage
                        System.out.println("Réponse du serveur: " + body);
                        return null;
                    }
                };
                clientRequest.start();
                clientRequest.join();
                break;
            }
        }
    }
}
