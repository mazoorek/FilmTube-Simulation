package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class Livestream extends Product implements Promotion {
    private String dateOfTheBegginingOfPromotion; //TODO promotion
    private String dateOfTheEndOfPromotion; //TODO promotion
    private int discountPercentageOfPromotion; //TODO promotion
    private String dateOfStream;
    private int dayOfTheYear;
    private GregorianCalendar dateOfLivestream = new GregorianCalendar();

    public Livestream(int productID, String description, ArrayList<String> countries, int year, ArrayList<String> cast, String title, ArrayList<String> genres,String distributorName, SimulationTimer simulationTimer) {
        super(productID,description, countries, year, cast, title, genres,distributorName );
        Random random = new Random();
        setDuration(random.nextInt(80) + 60);
        this.dayOfTheYear = simulationTimer.getDayOfYear() + random.nextInt(14);
        this.dateOfLivestream.set(Calendar.YEAR,simulationTimer.getYear());
        if (this.dayOfTheYear > dateOfLivestream.getActualMaximum(Calendar.DAY_OF_YEAR)) {
            this.dayOfTheYear = (dayOfTheYear + random.nextInt(14)) % dateOfLivestream.getActualMaximum(Calendar.DAY_OF_YEAR);
            this.dateOfLivestream.set(Calendar.YEAR, simulationTimer.getYear() + 1);
            this.dateOfLivestream.set(Calendar.DAY_OF_YEAR,this.dayOfTheYear);
        } else {
            this.dateOfLivestream.set(Calendar.YEAR, year);
            this.dateOfLivestream.set(Calendar.DAY_OF_YEAR, this.dayOfTheYear);
        }
    }

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

    public GregorianCalendar getDateOfLivestream() {
        return dateOfLivestream;
    }

    public void setDateOfLivestream(GregorianCalendar dateOfLivestream) {
        this.dateOfLivestream = dateOfLivestream;
    }
}
