package model;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Klasa opisująca dystrybutora, co robi, gdy pojawia się w programie jako nowy wątek, sposób wydawania produktów
 * oraz negocjacji umowy.
 */
public class Distributor implements LicenceAgreement, Runnable {
    private int amount;
    private String name;
    private String formOfPayment;
    private ArrayList<Product> distributorsProducts = new ArrayList<>();
    private ArrayList<Product> possibleProducts;
    private boolean toBeDeleted;
    private SimulationTimer simulationTimer;
    private boolean goingToRelease;
    private ProductsList productsOnFilmtube;
    private boolean goingToRenegotiateDeal;
    private int amountOfNewProductsOwnerWants;
    public Distributor(ArrayList<String> distributorNamesDatabase, SimulationTimer simulationTimer, ArrayList<Product> productsDatabase, ProductsList productsOnFilmtube) {
        Random random = new Random();
        if (distributorNamesDatabase.isEmpty()) {
            this.name = generateRandomString(random.nextInt(4) + 8);
        } else {
            int nameIndex = random.nextInt(distributorNamesDatabase.size());
            this.name = distributorNamesDatabase.get(nameIndex);
            distributorNamesDatabase.remove(nameIndex);
        }
        this.toBeDeleted = false;
        this.possibleProducts = new ArrayList<>(productsDatabase);
        this.simulationTimer = simulationTimer;
        this.productsOnFilmtube = productsOnFilmtube;
        this.goingToRelease = false;
        this.goingToRenegotiateDeal=false;
        this.amountOfNewProductsOwnerWants=0;
        negotiateDeal();
    }

    public Distributor() {
    }

    private String generateRandomString(int stringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public void negotiateDeal() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            this.formOfPayment = "monthly";
            this.amount = 30 * random.nextInt(70) + 30;
        } else {
            this.formOfPayment = "single product";
            this.amount = random.nextInt(5) + 10;//percent of product cost
        }
    }

    public void releaseProduct() {
        if (!possibleProducts.isEmpty()) {
            Random random = new Random();
            int randomTypeOfProduct;
            int numberOfProduct = random.nextInt(possibleProducts.size());
            String description = possibleProducts.get(numberOfProduct).getDescription();
            numberOfProduct = random.nextInt(possibleProducts.size());
            ArrayList<String> countries = possibleProducts.get(numberOfProduct).getCountriesOfProduction();
            numberOfProduct = random.nextInt(possibleProducts.size());
            int year = possibleProducts.get(numberOfProduct).getYear();
            numberOfProduct = random.nextInt(possibleProducts.size());
            ArrayList<String> cast = possibleProducts.get(numberOfProduct).getCast();
            numberOfProduct = random.nextInt(possibleProducts.size());
            ArrayList<String> genres = possibleProducts.get(numberOfProduct).getGenres();
            numberOfProduct = random.nextInt(possibleProducts.size());
            String title = possibleProducts.get(numberOfProduct).getTitle();
            possibleProducts.remove(numberOfProduct);
            int productID = productsOnFilmtube.getNewId();
            if (!possibleProducts.isEmpty()) {
                randomTypeOfProduct = random.nextInt(3);
            } else {
                randomTypeOfProduct = random.nextInt(2);
            }
            if (randomTypeOfProduct == 0) {
                Product film = new Film(productID,description, countries, year, cast, title,genres,this.name);
                productsOnFilmtube.add(film);
                distributorsProducts.add(film);
            } else if (randomTypeOfProduct == 1) {
                Product livestream = new Livestream(productID,description, countries, simulationTimer.getYear(), cast, title,genres, this.name,this.simulationTimer);
                productsOnFilmtube.add(livestream);
                distributorsProducts.add(livestream);
            } else if (randomTypeOfProduct == 2) {
                ArrayList<Season> seasons = new ArrayList<>();
                int numberOfSeasons = random.nextInt(5)+1;
                int numberOfEpisodes = random.nextInt(5)+8;
                int episodeId = 1;
                for(int i =0;i<numberOfSeasons;i++){
                    Season season = new Season(numberOfEpisodes);
                    ArrayList<Episode> episodes = new ArrayList<>();
                    for(int j = 0;j<numberOfEpisodes;j++){
                        String episodeTitle = possibleProducts.get(random.nextInt(possibleProducts.size())).getTitle();
                        Episode episode = new Episode(episodeId,episodeTitle,title,productID);
                        episodeId+=1;
                        episodes.add(episode);
                    }
                   season.setEpisodes(episodes);
                    seasons.add(season);
                }
                Product series = new Series(productID,description, countries, year, cast, title,genres,this.name, seasons);
                productsOnFilmtube.add(series);
                distributorsProducts.add(series);
            }
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getDistributorsProducts() {
        return distributorsProducts;
    }

    public void setDistributorsProducts(ArrayList<Product> distributorsProducts) {
        this.distributorsProducts = distributorsProducts;
    }

    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

    public boolean isGoingToRelease() {
        return this.goingToRelease;
    }

    public void setGoingToRelease(boolean goingToRelease) {
        this.goingToRelease = goingToRelease;
    }

    public boolean isGoingToRenegotiateDeal() {
        return goingToRenegotiateDeal;
    }

    public void setGoingToRenegotiateDeal(boolean goingToRenegotiateDeal) {
        this.goingToRenegotiateDeal = goingToRenegotiateDeal;
    }

    public String getFormOfPayment() {
        return formOfPayment;
    }

    public void setFormOfPayment(String formOfPayment) {
        this.formOfPayment = formOfPayment;
    }

    public int getAmountOfNewProductsOwnerWants() {
        return amountOfNewProductsOwnerWants;
    }

    public void setAmountOfNewProductsOwnerWants(int amountOfNewProductsOwnerWants) {
        this.amountOfNewProductsOwnerWants = amountOfNewProductsOwnerWants;
    }

    @Override
    public void run() {
        while (!this.toBeDeleted) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.goingToRelease){
                releaseProduct();
                this.goingToRelease=false;
            }
            if(this.goingToRenegotiateDeal){
                negotiateDeal();
                this.goingToRenegotiateDeal = false;
            }
            while(this.amountOfNewProductsOwnerWants>0){
                releaseProduct();
                this.amountOfNewProductsOwnerWants-=1;
            }
        }
    }
}
