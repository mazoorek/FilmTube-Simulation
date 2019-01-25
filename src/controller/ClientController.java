package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Client;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiedzialna za pokazywanie większej ilości informacji o Kliencie.
 */
public class ClientController implements Initializable {
    private static Client client;

    @FXML
    private Label nameLabel;

    @FXML
    private Label surnameLabel;

    @FXML
    private Label dateOfBirthLabel;

    @FXML
    private Label numberOfCreditCardLabel;

    @FXML
    private Label loginLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label subscriptionTypeLabel;

    @FXML
    private Label subscriptionPriceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client  = MainController.getSelectedClient();
        nameLabel.setText("Name: "+client.getName());
        surnameLabel.setText("Surname: "+client.getSurname());
        dateOfBirthLabel.setText("Date of birth: "+client.getDateOfBirth());
        numberOfCreditCardLabel.setText("Number of credit card: "+ client.getNumberOfCreditCard());
        loginLabel.setText("Login: " + client.getLogin());
        emailLabel.setText("Email: "+ client.getEmail());
        if(client.isHasSubscription()){
            subscriptionTypeLabel.setText("Type of subscription: "+client.getSubscriptionType());
            subscriptionPriceLabel.setText("Price of subscription: "+client.getSubscriptionValue());
        }else{
            subscriptionTypeLabel.setText("Type of subscription: Client don't have any subscription");
        }
    }
}
