package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Distributor;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiedzialna za pokazywanie większej ilości informacji o Dystrybutorze.
 */

public class DistributorController implements Initializable {

    private static Distributor distributor;

    @FXML
    private Label distributorNameLabel;

    @FXML
    private Label formOfPaymentLabel;

    @FXML
    private Label amountLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        distributor = MainController.getSelectedDistributor();
        distributorNameLabel.setText("Name: "+distributor.getName());
        formOfPaymentLabel.setText("Form of payment: "+ distributor.getFormOfPayment());
        amountLabel.setText("Amount: "+ distributor.getAmount());
    }

}
