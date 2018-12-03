package model;

public class Livestream extends Product implements Promotion {
    private String dateOfTheBegginingOfPromotion;
    private String dateOfTheEndOfPromotion;
    private int discountPercentageOfPromotion;
    private String dateOfStream;

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

    public String getDateOfStream() {
        return dateOfStream;
    }

    public void setDateOfStream(String dateOfStream) {
        this.dateOfStream = dateOfStream;
    }
}
