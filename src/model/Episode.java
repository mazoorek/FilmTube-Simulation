package model;
import java.util.Random;
public class Episode {
    private String Premiere;
    private String seriesName;
    private String title;
    private int id;
    private int length;
    public Episode(int id,String title,String seriesName){
        this.id=id;
        this.title =title;
        this.seriesName = seriesName;
        Random random = new Random();
        this.length = random.nextInt(40)+20;
    }
    public String getPremiere() {
        return Premiere;
    }

    public void setPremiere(String premiere) {
        Premiere = premiere;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
