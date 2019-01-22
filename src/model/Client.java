package model;

import java.util.*;
import static java.lang.Thread.sleep;

public class Client implements Runnable {
    private String name;
    private String surname;
    private String login;
    private String dateOfBirth;
    private String email;
    private String numberOfCreditCard;
    private ArrayList<Product> boughtFilmsAndLives = new ArrayList<>();
    private ArrayList<Episode> boughtEpisodes = new ArrayList<>();
    private ArrayList<Product> productsOnFilmtube;
    private boolean hasSubscription;
    private Subscription subscription;
    private boolean toBeDeleted;
    SimulationTimer simulationTimer;
    private boolean goingToWatch;
    private boolean goingToBuy;

    public Client(ArrayList<Product> productsOnFilmtube, Map<String, Subscription> subscriptions, SimulationTimer simulationTimer) {
        this.productsOnFilmtube = productsOnFilmtube;
        Random random = new Random();
        int nameLength = random.nextInt(4) + 8;
        int surnameLength = random.nextInt(4) + 8;
        int loginLength = random.nextInt(4) + 8;
        this.name = generateRandomString(nameLength, "letters");
        this.surname = generateRandomString(surnameLength, "letters");
        this.login = generateRandomString(loginLength, "letters");
        this.numberOfCreditCard = generateRandomString(loginLength, "numbers");
        this.email = generateRandomString(random.nextInt(7) + 8, "letters") + "@"
                + generateRandomString(random.nextInt(2) + 5, "letters") + ".com";
        this.dateOfBirth = getRandomDate();
        this.toBeDeleted = false;
        this.simulationTimer = simulationTimer;
        if (random.nextInt(5) == 0) {
            this.hasSubscription = true;
            int pickedSub = random.nextInt(3);
            if(pickedSub==0){
                this.subscription = subscriptions.get("basic");
            }else if(pickedSub==1){
                this.subscription = subscriptions.get("family");
            }else if(pickedSub==2){
                this.subscription = subscriptions.get("premium");
            }
        } else {
            this.hasSubscription = false;
        }
    }

    private String generateRandomString(int stringLength, String typeOfString) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        if (typeOfString.equals("numbers")) {
            leftLimit = 48;
            rightLimit = 57;
        }
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(stringLength);
        for (int i = 0; i < stringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private String getRandomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        Random random = new Random();
        int year = random.nextInt(100) + 1900;
        gc.set(Calendar.YEAR, year);
        int dayOfYear = random.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR)) + 1;
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
        return (gc.get(Calendar.YEAR) + "-" + (gc.get(Calendar.MONTH) + 1) + "-" + gc.get(Calendar.DAY_OF_MONTH));
    }

    public void buyProduct() { //user can watch livestreams that already have happended for instance livestreams of standups
        if(!this.productsOnFilmtube.isEmpty() && !this.hasSubscription){
            Random random = new Random();
            boolean picked = false;
            int id = 0;
            while(!picked){
                picked = true;
                id = random.nextInt(this.productsOnFilmtube.size());
                for(Product bp: boughtFilmsAndLives){
                    if(bp.getProductID()==this.productsOnFilmtube.get(id).getProductID()){
                        picked = false;
                    }
                }
            }
            int episodeId = 0;
            if(this.productsOnFilmtube.get(id) instanceof Series){
                Series series = (Series)this.productsOnFilmtube.get(id);
                if(!clientHasAlreadyBoughtAllEpisodes(series)){
                    boolean pickedEpisode = false;
                    while(!pickedEpisode){
                        pickedEpisode = true;
                        episodeId = random.nextInt(series.getNumberOfEpisodes());
                        for(Episode be:boughtEpisodes){
                            for(Season season:series.getSeasons()){
                                for(Episode e:season.getEpisodes()){
                                    if(be.getId()==e.getId()){
                                        pickedEpisode=false;
                                        break;
                                    }
                                }
                                if(!pickedEpisode)break;
                            }
                        }
                    }
                    boolean found = false;
                    for(Season season:series.getSeasons()){
                        for(Episode episode:season.getEpisodes()){
                            if(episode.getId()==episodeId){
                                boughtEpisodes.add(episode);
                                found = true;
                                break;
                            }
                            if(found)break;
                        }
                    }
                    this.productsOnFilmtube.get(id).setAmountOfNewBuyers(this.productsOnFilmtube.get(id).getAmountOfNewBuyers()+1); // if it is series, every episode of series costs the same
                                                                                                                                 // so this notify that some episode has been bought.
                }
            }else{
                boughtFilmsAndLives.add(this.productsOnFilmtube.get(id));
                if(boughtFilmsAndLives.get(boughtFilmsAndLives.size()-1) instanceof Film){
                    Film film = (Film) boughtFilmsAndLives.get(boughtFilmsAndLives.size()-1);
                    film.setAccessEndDate(this.simulationTimer);
                }
                this.productsOnFilmtube.get(id).setAmountOfNewBuyers(this.productsOnFilmtube.get(id).getAmountOfNewBuyers()+1);
            }

        }
    }

    public void watchProduct(){ // Client can see recording of Livestream even after the live
        Random random = new Random();
        if(!this.hasSubscription){
            boolean filmsAndLives = false;
            boolean series = false;
            ArrayList<Product> currentProducts = new ArrayList<>(this.productsOnFilmtube);
            if(this.boughtFilmsAndLives.isEmpty() && this.boughtEpisodes.isEmpty()){
                return;
            }
            if(!this.boughtFilmsAndLives.isEmpty() && !this.boughtEpisodes.isEmpty()){
                int choosingOption = random.nextInt(2);
                if(choosingOption==0)series=true;
                if(choosingOption==1)filmsAndLives=true;
            }
            if(series || !this.boughtEpisodes.isEmpty()){
                int episodeId = random.nextInt(this.boughtEpisodes.size());
                for(Product p:currentProducts){
                    if(p instanceof Series){
                        if(this.boughtEpisodes.get(episodeId).getSeriesName().equals(p.getTitle())){
                            p.setNumberOfViews(p.getNumberOfViews()+1);
                            p.setViewsInMonth(p.getViewsInMonth()+1);
                            break;
                        }
                    }
                }
            }
            if(filmsAndLives || !this.boughtFilmsAndLives.isEmpty()){
                int id = random.nextInt(this.boughtFilmsAndLives.size());
                if(this.boughtFilmsAndLives.get(id) instanceof Film){
                    Film film = (Film)this.boughtFilmsAndLives.get(id);
                    if(!filmAccessHasExpired(film)){
                        for(Product p: currentProducts){
                            if(film.getProductID()==p.getProductID()){
                                p.setNumberOfViews(p.getNumberOfViews()+1);
                                p.setViewsInMonth(p.getViewsInMonth()+1);
                                break;
                            }
                        }
                    }else{
                        this.boughtFilmsAndLives.remove(this.boughtFilmsAndLives.get(id));
                    }
                }else{
                    Livestream livestream = (Livestream)this.boughtFilmsAndLives.get(id);
                    boolean canWatch = false;
                    if(livestream.getDateOfLivestream().get(Calendar.YEAR)==simulationTimer.getYear()){
                        if(livestream.getDateOfLivestream().get(Calendar.DAY_OF_YEAR)<=simulationTimer.getDayOfYear()){
                            canWatch = true;
                        }
                    }else if(livestream.getDateOfLivestream().get(Calendar.YEAR)<simulationTimer.getYear()){
                        canWatch = true;
                    }
                    if(canWatch){
                        for(Product p: currentProducts){
                            if(livestream.getProductID()==p.getProductID()){
                                p.setNumberOfViews(p.getNumberOfViews()+1);
                                p.setViewsInMonth(p.getViewsInMonth()+1);
                                break;
                            }
                        }
                    }
                }
            }
        }else{
            ArrayList<Product> currentProducts = new ArrayList<>(this.productsOnFilmtube);
            if(currentProducts.isEmpty())return;
            boolean canWatch = true;
            do{
                canWatch = true;
                int id = random.nextInt(currentProducts.size());
                if(currentProducts.get(id) instanceof Livestream){
                    canWatch = false;
                    Livestream livestream = (Livestream)currentProducts.get(id);
                    if(livestream.getDateOfLivestream().get(Calendar.YEAR)==simulationTimer.getYear()){
                        if(livestream.getDateOfLivestream().get(Calendar.DAY_OF_YEAR)<=simulationTimer.getDayOfYear()){
                            canWatch = true;
                        }
                    }else if(livestream.getDateOfLivestream().get(Calendar.YEAR)<simulationTimer.getYear()){
                        canWatch = true;
                    }
                }
                if(canWatch){
                    currentProducts.get(id).setNumberOfViews(currentProducts.get(id).getNumberOfViews()+1);
                    currentProducts.get(id).setViewsInMonth(currentProducts.get(id).getViewsInMonth()+1);
                }
            }while(!canWatch);
        }
    }

    public boolean filmAccessHasExpired(Film film){
        if(film.getAccessEndDate().get(Calendar.YEAR)<simulationTimer.getYear()){
            return true;
        }else if(film.getAccessEndDate().get(Calendar.YEAR)==simulationTimer.getYear()){
            if(film.getAccessEndDate().get(Calendar.DAY_OF_YEAR)<simulationTimer.getDayOfYear()){
                return true;
            }
        }
        return false;
    }
    public boolean clientHasAlreadyBoughtAllEpisodes(Series series){
        int numberOfBoughtEpisodesOfThisSeries = 0;
        for(Episode be:this.boughtEpisodes){
            if(be.getSeriesName().equals(series.getTitle())){
                numberOfBoughtEpisodesOfThisSeries+=1;
            }
        }
        return numberOfBoughtEpisodesOfThisSeries == series.getNumberOfEpisodes();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberOfCreditCard() {
        return numberOfCreditCard;
    }

    public void setNumberOfCreditCard(String numberOfCreditCard) {
        this.numberOfCreditCard = numberOfCreditCard;
    }

    public ArrayList<Product> getBoughtFilmsAndLives() {
        return boughtFilmsAndLives;
    }

    public void setBoughtFilmsAndLives(ArrayList<Product> boughtFilmsAndLives) {
        this.boughtFilmsAndLives = boughtFilmsAndLives;
    }

    public boolean isHasSubscription() {
        return hasSubscription;
    }

    public void setHasSubscription(boolean hasSubscription) {
        this.hasSubscription = hasSubscription;
    }

    public boolean isToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

    public boolean isGoingToWatch() {
        return goingToWatch;
    }

    public void setGoingToWatch(boolean goingToWatch) {
        this.goingToWatch = goingToWatch;
    }

    public boolean isGoingToBuy() {
        return goingToBuy;
    }

    public void setGoingToBuy(boolean goingToBuy) {
        this.goingToBuy = goingToBuy;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
    public int getSubscriptionValue(){
        return this.subscription.getSubscriptionPrice();
    }
    public void setSubscriptionValue(int price){
        this.subscription.setSubscriptionPrice(price);
    }
    public String getSubscriptionType(){
        return this.subscription.getSubscriptionType();
    }


    @Override
    public void run() {
        while (!this.toBeDeleted) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.goingToBuy){
                if(!this.hasSubscription){
                    buyProduct();
                    this.goingToBuy=false;
                }
            }
            if(this.goingToWatch){
                watchProduct();
                this.goingToWatch = false;
            }
        }
        System.out.println("Cilient deleted");
    }
}
