package model;
import java.util.ArrayList;
import java.util.Random;
public class Product {
    private String title;
    private String description;
    private String image; //path to image
    private int year;
    private int duration; // in minutes
    private String distributorName;
    private ArrayList<String> countriesOfProduction;
    private ArrayList<String> genres;
    private ArrayList<String> cast;
    private int cost;
    private int numberOfViews;
    private int viewsInMonth;
    private ArrayList<Integer> viewsEachMonthSinceReleased = new ArrayList<>();
    private String dateOfAddingToTheSystem;
    private int amountOfNewBuyers;
    private int productID;
    public Product(){

    }
    public Product(int productID, String description,ArrayList<String> countries,int year,ArrayList<String> cast, String title,ArrayList<String>genres,String distributorName){
        this.distributorName=distributorName;
        this.genres = genres;
        this.description=description;
        this.countriesOfProduction=countries;
        this.year=year;
        this.cast=cast;
        this.title=title;
        Random random = new Random();
        int numberOfImagesInDatabase = 20;
        this.image = String.valueOf(random.nextInt(numberOfImagesInDatabase)+1);
        this.numberOfViews = 0;
        this.cost = random.nextInt(70)+30;
        this.viewsInMonth = 0;
        this.numberOfViews = 0;
        this.amountOfNewBuyers = 0;
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<String> getCountriesOfProduction() {
        return countriesOfProduction;
    }

    public void setCountriesOfProduction(ArrayList<String> countriesOfProduction) {
        this.countriesOfProduction = countriesOfProduction;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public synchronized void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public String getDateOfAddingToTheSystem() {
        return dateOfAddingToTheSystem;
    }

    public void setDateOfAddingToTheSystem(String dateOfAddingToTheSystem) {
        this.dateOfAddingToTheSystem = dateOfAddingToTheSystem;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public int getViewsInMonth() {
        return viewsInMonth;
    }

    public synchronized void setViewsInMonth(int viewsInMonth) {
        this.viewsInMonth = viewsInMonth;
    }

    public ArrayList<Integer> getViewsEachMonthSinceReleased() {
        return viewsEachMonthSinceReleased;
    }

    public void setViewsEachMonthSinceReleased(ArrayList<Integer> viewsEachMonthSinceReleased) {
        this.viewsEachMonthSinceReleased = viewsEachMonthSinceReleased;
    }
    public void addNextMonthViews(Integer views){
        this.viewsEachMonthSinceReleased.add(views);
    }

    public int getAmountOfNewBuyers() {
        return amountOfNewBuyers;
    }

    public void setAmountOfNewBuyers(int amountOfNewBuyers) {
        this.amountOfNewBuyers = amountOfNewBuyers;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
