package edu.ezip.ing1.pds.front.Controller;

import edu.ezip.ing1.pds.business.dto.Stock;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StockQuantityAlertPane extends VBox {
    public StockQuantityAlertPane(Set<Stock> medicaments) {
        this.setSpacing(20);
        // Chargement du fichier CSS
        try {
            String css = getClass().getResource("/resources-css/AlertPaneStage.css").toURI().toString();
            this.getStylesheets().add(css);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        displayStockQuantityAlert(medicaments);
    }

    private void displayStockQuantityAlert(Set<Stock> medicaments) {
        Platform.runLater(() -> {
            Alert alertDialog = new Alert(Alert.AlertType.INFORMATION);
            alertDialog.setTitle("Alertes de Stock");
            alertDialog.setHeaderText(null);
            alertDialog.setResizable(true);
            alertDialog.setWidth(600);
            alertDialog.setHeight(800);

            VBox alertContent = new VBox();
            alertContent.setSpacing(20);

            // Titre principal
            Label mainTitle = new Label("Alertes de Stock");
            mainTitle.getStyleClass().add("alert-title");
            alertContent.getChildren().add(mainTitle);

            // Titre des alertes de quantité
            Label quantityTitle = new Label("Alertes de quantité :");
            quantityTitle.getStyleClass().add("alert-subtitle");
            alertContent.getChildren().add(quantityTitle);

            // Sous-titre des alertes de quantité avec seuil atteint
            Label reachedThresholdTitle = new Label("Alertes de quantité avec seuil atteint :");
            reachedThresholdTitle.getStyleClass().add("alert-subtitle");
            alertContent.getChildren().add(reachedThresholdTitle);

            // Obtenir la date actuelle
            LocalDate currentDate = LocalDate.now();

            // Listes d'alertes
            List<HBox> alertMessagesQuantiteAtteint = new ArrayList<>();
            List<HBox> alertMessagesQuantiteInferieur = new ArrayList<>();
            List<HBox> alertMessagesDateExpiration = new ArrayList<>();

            // Traiter chaque médicament
            for (Stock stock : medicaments) {
                String medicamentDetails = "💊 Médicament : " + stock.getNom() + " (Lot ID : " + stock.getMedicament_id() + ")";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                LocalDate seuilDate = LocalDate.parse(stock.getSeuil_date(), formatter);
                LocalDate expirationDate = LocalDate.parse(stock.getDate_expiration(), formatter);

                // Vérifier la quantité
                if (stock.getQuantite().equals(stock.getSeuil())) {
                    Label icon = new Label("✔️");
                    icon.getStyleClass().add("alert-icon");
                    Label medicamentLabel = new Label(medicamentDetails + " - Quantité : " + stock.getQuantite() + " (Seuil atteint)");
                    medicamentLabel.getStyleClass().add("alert-message");
                    HBox messageBox = new HBox(icon, medicamentLabel);
                    messageBox.getStyleClass().add("alert-quantite-atteint");
                    alertMessagesQuantiteAtteint.add(messageBox);
                } else if (stock.getQuantite() < stock.getSeuil()) {
                    Label icon = new Label("⚠️");
                    icon.getStyleClass().add("alert-icon");
                    Label medicamentLabel = new Label(medicamentDetails + " - Quantité : " + stock.getQuantite() + " (Quantité inférieure au seuil)");
                    medicamentLabel.getStyleClass().add("alert-message");
                    HBox messageBox = new HBox(icon, medicamentLabel);
                    messageBox.getStyleClass().add("alert-quantite-inferieur");
                    alertMessagesQuantiteInferieur.add(messageBox);
                }

                // Vérifier les dates d'expiration et de seuil
                if (currentDate.equals(seuilDate)) {
                    Label icon = new Label("📅");
                    icon.getStyleClass().add("alert-icon");
                    Label medicamentLabel = new Label(medicamentDetails + " - Date actuelle : " + currentDate + " (Date de seuil atteinte)");
                    medicamentLabel.getStyleClass().add("alert-message");
                    HBox messageBox = new HBox(icon, medicamentLabel);
                    messageBox.getStyleClass().add("alert-date-expiration");
                    alertMessagesDateExpiration.add(messageBox);
                }

                if (currentDate.isAfter(seuilDate) && currentDate.isBefore(expirationDate)) {
                    Label icon = new Label("⏳");
                    icon.getStyleClass().add("alert-icon");
                    Label medicamentLabel = new Label(medicamentDetails + " - Date actuelle : " + currentDate + " (Date de seuil dépassée, expiration non atteinte)");
                    medicamentLabel.getStyleClass().add("alert-message");
                    HBox messageBox = new HBox(icon, medicamentLabel);
                    messageBox.getStyleClass().add("alert-date-expiration");
                    alertMessagesDateExpiration.add(messageBox);
                }

                if (currentDate.isEqual(expirationDate) || currentDate.isAfter(expirationDate)) {
                    Label icon = new Label("❗");
                    icon.getStyleClass().add("alert-icon");
                    Label medicamentLabel = new Label(medicamentDetails + " - Date d'expiration : " + stock.getDate_expiration() + " (Date d'expiration dépassée)");
                    medicamentLabel.getStyleClass().add("alert-message");
                    HBox messageBox = new HBox(icon, medicamentLabel);
                    messageBox.getStyleClass().add("alert-date-expiration");
                    alertMessagesDateExpiration.add(messageBox);
                }
            }

            // Ajouter les alertes de quantité avec seuil atteint
            if (!alertMessagesQuantiteAtteint.isEmpty()) {
                alertMessagesQuantiteAtteint.forEach(alertContent.getChildren()::add);
            }

            // Sous-titre des alertes de quantité inférieures au seuil
            Label belowThresholdTitle = new Label("Alertes de quantité inférieure au seuil :");
            belowThresholdTitle.getStyleClass().add("alert-subtitle");
            alertContent.getChildren().add(belowThresholdTitle);

            // Ajouter les alertes de quantité inférieures au seuil
            if (!alertMessagesQuantiteInferieur.isEmpty()) {
                alertMessagesQuantiteInferieur.forEach(alertContent.getChildren()::add);
            }

            // Titre des alertes de date d'expiration
            Label expirationTitle = new Label("Alertes de date d'expiration :");
            expirationTitle.getStyleClass().add("alert-subtitle");
            alertContent.getChildren().add(expirationTitle);

            // Ajouter les alertes de date d'expiration
            if (!alertMessagesDateExpiration.isEmpty()) {
                alertMessagesDateExpiration.forEach(alertContent.getChildren()::add);
            }

            alertDialog.getDialogPane().setContent(alertContent);
            alertDialog.showAndWait();
        });
    }
}