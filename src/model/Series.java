package model;

import java.util.ArrayList;

public class Series extends Product implements Subscription {

    private String versionOfSubscription;
    private int priceOfSubscription;
    private int numberOfDevicesOfSubscription;
    private String maxResolutionOfProduct;
    private int numberOfSeasons;
    private ArrayList<Season> seasons;

    public String getVersionOfSubscription() {
        return versionOfSubscription;
    }

    public void setVersionOfSubscription(String versionOfSubscription) {
        this.versionOfSubscription = versionOfSubscription;
    }

    public int getPriceOfSubscription() {
        return priceOfSubscription;
    }

    public void setPriceOfSubscription(int priceOfSubscription) {
        this.priceOfSubscription = priceOfSubscription;
    }

    public int getNumberOfDevicesOfSubscription() {
        return numberOfDevicesOfSubscription;
    }

    public void setNumberOfDevicesOfSubscription(int numberOfDevicesOfSubscription) {
        this.numberOfDevicesOfSubscription = numberOfDevicesOfSubscription;
    }

    public String getMaxResolutionOfProduct() {
        return maxResolutionOfProduct;
    }

    public void setMaxResolutionOfProduct(String maxResolutionOfProduct) {
        this.maxResolutionOfProduct = maxResolutionOfProduct;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
}
