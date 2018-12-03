package model;
import java.util.ArrayList;
public class Product {
    private String name;
    private String description;
    private String photo; //path to photo
    private String dateOfProduction;
    private int duration; // in minutes
    //TODO dystrybutor
    private ArrayList<String> countriesOfProduction;
    private ArrayList<String> genres;
    private int cost;
    private int numBerOfViews;
    private String dateOfAddingToTheSystem;

    public void edit(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(String dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
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

    public int getNumBerOfViews() {
        return numBerOfViews;
    }

    public void setNumBerOfViews(int numBerOfViews) {
        this.numBerOfViews = numBerOfViews;
    }

    public String getDateOfAddingToTheSystem() {
        return dateOfAddingToTheSystem;
    }

    public void setDateOfAddingToTheSystem(String dateOfAddingToTheSystem) {
        this.dateOfAddingToTheSystem = dateOfAddingToTheSystem;
    }
}
