package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiedzialna za pokazywanie większej ilości informacji o produkcie oraz jego wykresu oglądalności
 * w zależności od czasu.
 */

public class ProductController implements Initializable {
    private static Product product;
    @FXML
    private ImageView productImageView;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label titleLabel;

    @FXML
    private Label yearLabel;

    @FXML
    private Label durationLabel;

    @FXML
    private Label distributorNameLabel;

    @FXML
    private Label countriesOfProductionLabel;

    @FXML
    private Label genresLabel;

    @FXML
    private Label castLabel;

    @FXML
    private Label costLabel;

    @FXML
    private Label numberOfViewsLabel;

    @FXML
    private Label viewsThisMonthLabel;

    @FXML
    private Label productIdLabel;

    @FXML
    private LineChart<String, Number> viewsEachMonthPlot;

    @FXML
    private CategoryAxis eachMonth;

    @FXML
    private NumberAxis numberOfViews;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        product = MainController.getSelectedProduct();
        productImageView.setImage(new Image(String.valueOf(getClass().getResource("../resources/images/"+product.getImage()+".jpg"))));
        descriptionLabel.setText("Description: "+ product.getDescription());
        titleLabel.setText("Title: "+ product.getTitle());
        yearLabel.setText("Year: "+product.getYear());
        durationLabel.setText("Duration: "+product.getDuration());
        distributorNameLabel.setText("Distributor name: "+product.getDistributorName());
        countriesOfProductionLabel.setText("Countries of production: "+product.getCountriesOfProduction());
        genresLabel.setText("Genres: "+product.getGenres());
        castLabel.setText("Cast: "+ product.getCast());
        costLabel.setText("Cost: "+product.getCost());
        numberOfViewsLabel.setText("Number of views: "+product.getNumberOfViews());
        viewsThisMonthLabel.setText("Views this month: "+ product.getViewsInMonth());
        productIdLabel.setText("Product id: "+ product.getProductID());
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        eachMonth.setLabel("Number of mont");
        numberOfViews.setLabel("Number of views");
        for(int i = 1;i<=product.getViewsEachMonthSinceReleased().size();i++){
            series.getData().add(new XYChart.Data<>(Integer.toString(i),product.getViewsEachMonthSinceReleased().get(i-1)));
        }
        viewsEachMonthPlot.getData().add(series);
    }
}
