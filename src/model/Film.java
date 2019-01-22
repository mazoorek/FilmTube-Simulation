package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Film extends Product implements Promotion {
    private String dateOfTheBeginningOfPromotion; //TODO promotion
    private String dateOfTheEndOfPromotion; //TODO promotion
    private int discountPercentageOfPromotion; //TODO promotion
    //    private String versionOfSubscription;
//    private int priceOfSubscription;
    private int accessTime;
    private GregorianCalendar accessEndDate = new GregorianCalendar();
    public Film(int productID,String description, ArrayList<String> countries, int year, ArrayList<String> cast, String title,ArrayList<String>genres,String distributorName) {
        super(productID,description,countries,year,cast,title,genres,distributorName);
        Random random = new Random();
        this.accessTime = random.nextInt(7) + 1;

        setDuration(random.nextInt(80)+60);
    }

    public String getDateOfTheBeginningOfPromotion() {
        return dateOfTheBeginningOfPromotion;
    }

    public void setDateOfTheBeginningOfPromotion(String dateOfTheBeginningOfPromotion) {
        this.dateOfTheBeginningOfPromotion = dateOfTheBeginningOfPromotion;
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

    public int getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(int accessTime) {
        this.accessTime = accessTime;
    }

    public GregorianCalendar getAccessEndDate() {
        return accessEndDate;
    }

    public void setAccessEndDate(SimulationTimer simulationTimer) {
        int dayOfYear = simulationTimer.getDayOfYear();
        this.accessEndDate.set(Calendar.YEAR,simulationTimer.getYear());
        if(dayOfYear>accessEndDate.getActualMaximum(Calendar.DAY_OF_YEAR)){
            dayOfYear = dayOfYear % this.accessEndDate.getActualMaximum(Calendar.DAY_OF_YEAR);
            this.accessEndDate.set(Calendar.DAY_OF_YEAR,dayOfYear);
            this.accessEndDate.set(Calendar.YEAR, simulationTimer.getYear() + 1);
        }else{
            this.accessEndDate.set(Calendar.DAY_OF_YEAR,dayOfYear);
        }
    }
}
