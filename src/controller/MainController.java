package controller;

//import com.jfoenix.controls.JFXListView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Client;
import model.Distributor;
import model.Product;
import model.Main;

/**
 * Główna scena symulacji, pokazywany jest tu aktualny dzień symulacji, data, stan konta i listy produktów,
 * dystrybutorów i klientów. Ekran odświeża się co sekundę.
 */

public class MainController extends ListView<Product> implements Initializable {
    @FXML
    private Button AddProductButton;

    @FXML
    private Button AddClientButton;

    @FXML
    private Button AddDistributorButton;

    @FXML
    private ListView<Product> productListView;

    @FXML
    private ListView<Client> clientListView;

    @FXML
    private ListView<Distributor> distributorListView;

    ObservableList<Product> productObservableList;
    ObservableList<Distributor> distributorObservableList;
    ObservableList<Client> clientObservableList;

    @FXML
    private VBox vbox;

    @FXML
    private Label dayOfSimulationLabel;
    private String dayOfSimulationText;
    @FXML
    private Label dateOfSimulationLabel;
    private String dateOfSimulationText;
    @FXML
    private Label monthlyBalanceLabel;
    private String monthlyBalanceText;
    @FXML
    private Label overallBalanceLabel;
    private String overallBalanceText;
    @FXML
    private Button endSimulationButton;

    @FXML
    private Label endOfSimulationLabel;

    private static Product selectedProduct;
    private static Distributor selectedDistributor;
    private static Client selectedClient;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    dayOfSimulationText = ("Day of Simulation: " + Main.getSimulationTimer().getDayOfSimulation());
                    dateOfSimulationText = ("Date: " + Main.getSimulationTimer().getDayOfMonth() + "-" + Main.getSimulationTimer().getMonth() + "-" +
                            Main.getSimulationTimer().getYear());
                    monthlyBalanceText = ("Monthly balance: " + Main.getOwner().getMonthlyBalance());
                    overallBalanceText = ("Overall balance: " + Main.getOwner().getOverallBalance());
                    dayOfSimulationLabel.setText(dayOfSimulationText);
                    dateOfSimulationLabel.setText(dateOfSimulationText);
                    monthlyBalanceLabel.setText(monthlyBalanceText);
                    overallBalanceLabel.setText(overallBalanceText);
                    endOfSimulationLabel.setText("");
                    productListView.getItems().clear();
                    productObservableList = FXCollections.observableArrayList(Main.getProductList().getProductsOnFilmtube());
                    productListView.getItems().addAll(productObservableList);
                    productListView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {
                        @Override
                        public ListCell<Product> call(ListView<Product> param) {
                            return new productCell();
                        }
                    });


                    distributorListView.getItems().clear();
                    distributorObservableList = FXCollections.observableArrayList(Main.getDistributors());
                    distributorListView.getItems().addAll(distributorObservableList);
                    distributorListView.setCellFactory(new Callback<ListView<Distributor>, ListCell<Distributor>>() {
                        @Override
                        public ListCell<Distributor> call(ListView<Distributor> param) {
                            return new distributorCell();
                        }
                    });


                    clientListView.getItems().clear();
                    clientObservableList = FXCollections.observableArrayList(Main.getClients());
                    clientListView.getItems().addAll(clientObservableList);
                    clientListView.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
                        @Override
                        public ListCell<Client> call(ListView<Client> param) {
                            return new clientCell();
                        }
                    });
                    if (Main.getOwner().isEndOfSimulation()) {
                        endOfSimulationLabel.setText("Simulation has ended");
                        timer.cancel();
                        timer.purge();
                    }
                });
            }
        }, 0, 1000);
    }

    static class productCell extends ListCell<Product> {
        HBox hbox = new HBox();
        Label titleLabel = new Label("");
        Label idLabel = new Label("");
        Pane pane = new Pane();
        Button showMoreButton = new Button("show more");
        Button deleteButton = new Button("delete");
        Product lastItem;

        public productCell() {
            super();
            hbox.getChildren().addAll(titleLabel, pane, showMoreButton, deleteButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            showMoreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Product p : Main.getProductList().getProductsOnFilmtube()) {
                        if (p.getProductID() == Integer.parseInt(idLabel.getText())) {
                            MainController.selectedProduct = p;
                            try {
                                Stage productStage = new Stage();
                                Parent root = FXMLLoader.load(getClass().getResource("/view/productInfo.fxml"));
                                productStage.setTitle("Product Info");
                                productStage.setScene(new Scene(root, 1000, 800));
                                productStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            });
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Product p : Main.getProductList().getProductsOnFilmtube()) {
                        if (p.getProductID() == Integer.parseInt(idLabel.getText())) {
                            p.setToBeRemoved(true);
                            break;
                        }
                    }
                }
            });
        }

        @Override
        protected void updateItem(Product product, boolean empty) {
            super.updateItem(product, empty);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = product;
                titleLabel.setText(product != null ? product.getTitle() : "<null>");
                idLabel.setText(product != null ? Integer.toString(product.getProductID()) : "<null>");
                setGraphic(hbox);
            }
        }
    }

    static class distributorCell extends ListCell<Distributor> {
        HBox hbox = new HBox();
        Label titleLabel = new Label("");
        Pane pane = new Pane();
        Button showMoreButton = new Button("show more");
        Button deleteButton = new Button("delete");
        Distributor lastItem;

        public distributorCell() {
            super();
            hbox.getChildren().addAll(titleLabel, pane, showMoreButton, deleteButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            showMoreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Distributor d : Main.getDistributors()) {
                        if (d.getName().equals(titleLabel.getText())) {
                            MainController.selectedDistributor = d;
                            try {
                                Stage distributorStage = new Stage();
                                Parent root = FXMLLoader.load(getClass().getResource("/view/distributorInfo.fxml"));
                                distributorStage.setTitle("Distributor Info");
                                distributorStage.setScene(new Scene(root, 400, 400));
                                distributorStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            });
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Platform.runLater(() -> {
                        for (Product p : Main.getProductList().getProductsOnFilmtube()) {
                            if (p.getDistributorName().equals(titleLabel.getText())) {
                                System.out.println(p.getTitle());
                                p.setToBeRemoved(true);
                            }
                        }
                        for (Distributor d : Main.getDistributors()) {
                            if (d.getName().equals(titleLabel.getText())) {
                                d.setToBeDeleted(true);
                                break;
                            }
                        }
                    });
                }
            });
        }

        @Override
        protected void updateItem(Distributor distributor, boolean empty) {
            super.updateItem(distributor, empty);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = distributor;
                titleLabel.setText(distributor != null ? distributor.getName() : "<null>");
                setGraphic(hbox);
            }
        }
    }

    static class clientCell extends ListCell<Client> {
        HBox hbox = new HBox();
        Label loginLabel = new Label("");
        Pane pane = new Pane();
        Button showMoreButton = new Button("show more");
        Button deleteButton = new Button("delete");
        Client lastItem;

        public clientCell() {
            super();
            hbox.getChildren().addAll(loginLabel, pane, showMoreButton, deleteButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            showMoreButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Client c : Main.getClients()) {
                        if (c.getLogin().equals(loginLabel.getText())) {
                            MainController.selectedClient = c;
                            try {
                                Stage clientStage = new Stage();
                                Parent root = FXMLLoader.load(getClass().getResource("/view/clientInfo.fxml"));
                                clientStage.setTitle("Client Info");
                                clientStage.setScene(new Scene(root, 400, 400));
                                clientStage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Client c : Main.getClients()) {
                        if (c.getLogin().equals(loginLabel.getText())) {
                            c.setToBeDeleted(true);
                        }
                    }
                }
            });
        }

        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = client;
                loginLabel.setText(client != null ? client.getLogin() : "<null>");
                setGraphic(hbox);
            }
        }
    }

    @FXML
    void addDistributor(ActionEvent event) {
        Main.getOwner().addDistributor();
    }

    @FXML
    void addClient(ActionEvent event) {
        Main.getOwner().addClient();
    }

    @FXML
    void addProduct(ActionEvent event) {
        Main.getOwner().addProduct();
    }

    @FXML
    void endSimulation(ActionEvent event) {
        Main.getOwner().setEndOfSimulation(true);
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }

    public static Distributor getSelectedDistributor() {
        return selectedDistributor;
    }

    public static Client getSelectedClient() {
        return selectedClient;
    }

    public void setAddProductButton(Button addProductButton) {
        AddProductButton = addProductButton;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public static void setSelectedDistributor(Distributor selectedDistributor) {
        selectedDistributor = selectedDistributor;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }
}
