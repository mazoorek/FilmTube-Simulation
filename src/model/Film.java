package model;

public class Film extends Product implements Promotion,Subscription{
    private String dateOfTheBegginingOfPromotion;
    private String dateOfTheEndOfPromotion;
    private int discountPercentageOfPromotion;
    private String versionOfSubscription;
    private int priceOfSubscription;
    private int numberOfDevicesOfSubscription;
    private String maxResolutionOfProduct;
    private String linkToTrailer;
    private int accessTime;

    public String getDateOfTheBegginingOfPromotion() {
        return dateOfTheBegginingOfPromotion;
    }

    public void setDateOfTheBegginingOfPromotion(String dateOfTheBegginingOfPromotion) {
        this.dateOfTheBegginingOfPromotion = dateOfTheBegginingOfPromotion;
    }

    public String getDateOfTheEndOfPromotion() {
        return dateOfTheEndOfPromotion;
    }

    public void setDateOfTheEndOfPromotion(String dateOfTheEndOfPromotion) {
        this.dateOfTheEndOfPromotion = dateOfTheEndOfPromotion;
    }

    public int getDiscountPercentageOfPromotion() {
        return discountPercentageOfPromotion;
    }

    public void setDiscountPercentageOfPromotion(int discountPercentageOfPromotion) {
        this.discountPercentageOfPromotion = discountPercentageOfPromotion;
    }

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

    public String getLinkToTrailer() {
        return linkToTrailer;
    }

    public void setLinkToTrailer(String linkToTrailer) {
        this.linkToTrailer = linkToTrailer;
    }

    public int getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }
}
