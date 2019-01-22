package model;

import java.util.ArrayList;
import java.util.Random;
public class Series extends Product {

//    private String versionOfSubscription;
//    private int priceOfSubscription;
//    private int numberOfDevicesOfSubscription;
//    private String maxResolutionOfProduct;
    private int numberOfSeasons;
    private ArrayList<Season> seasons;
    private int numberOfEpisodes;
    public Series(int productID, String description, ArrayList<String> countries, int year, ArrayList<String> cast, String title,ArrayList<String> genres,String distributorName,ArrayList<Season> seasons){
        super(productID,description,countries,year,cast,title,genres,distributorName);
        this.seasons=seasons;
       Random random = new Random();
       setDuration(random.nextInt(40)+20);
       this.numberOfEpisodes = 0;
       for(Season s:seasons){
           this.numberOfEpisodes+=s.getNumberOfEpisodes();
       }
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

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
