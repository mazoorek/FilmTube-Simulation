package model;

import java.util.ArrayList;

public class Season {
    private ArrayList<Episode> episodes;
    private int numberOfEpisodes;
    public Season(int numberOfEpisodes){
        this.numberOfEpisodes=numberOfEpisodes;
    }
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }
}
