package controller;

//import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import model.Product;
import model.Main;

public class MainController extends ListView<Product> implements Initializable {
    @FXML
    private Button AddProductButton;

    @FXML
    private Button AddClientButton;

    @FXML
    private Button AddDistributorButton;

    ObservableList<String> observableDistributorsList;
    //@FXML
    //private JFXListView<Product> productListView;

    ObservableList<Product> productObservableList = FXCollections.observableArrayList(Main.getProductList().getProductsOnFilmtube());
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        productListView.setItems(productObservableList);
//        productListView.setCellFactory(param -> new ProductCell());
}

//    static class ProductCell extends  ListCell<Product>{
//        HBox hbox = new HBox();
//        Button showMoreButton = new Button("Show More");
//        Label labelName = new Label("");
//        Label labelId = new Label("");
//        Pane pane = new Pane();
//
//
//        public ProductCell(){
//            super();
//            hbox.getChildren().addAll(labelName,pane,showMoreButton);
//            hbox.setHgrow(pane, Priority.ALWAYS);
//            showMoreButton.setOnAction(new EventHandler<ActionEvent>() {
//                @Override
//                public void handle(ActionEvent actionEvent) {
//                    System.out.println(labelName.getText());
//                    showClientInfo();
//                }
//            });
//        }
//        public synchronized void updateItem(Product p,boolean empty){
//            super.updateItem(p,empty);
//            if(empty || p==null){
//                setText(null);
//                setGraphic(null);
//            }else{
//                labelName.setText(p.getTitle());
//                labelId.setText(Integer.toString(p.getProductID()));
//                setGraphic(hbox);
//            }
//        }
//    }
//
//    public static void showClientInfo(){
//
//    }


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


}
