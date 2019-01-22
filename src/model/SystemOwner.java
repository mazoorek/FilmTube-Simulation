package model;

import java.util.Map;
import java.util.HashMap;

public class SystemOwner {
    private int overallBalance;
    private int monthlyBalance;
    private boolean endOfSimulation;
    private int distributorsToAdd;
    private int clientsToAdd;
    private int productsToAdd;
    private int monthsOfRowWithLoss;
    Map<String, Subscription> subscriptions = new HashMap<>();

    public SystemOwner() {
        this.overallBalance = 0;
        this.monthlyBalance = 0;
        this.endOfSimulation = false;
        this.distributorsToAdd = 3;
        this.clientsToAdd = 0;
        this.productsToAdd = 0;
        this.monthsOfRowWithLoss = 0;
        setStartingSubscriptionsValues();
    }

    private void setStartingSubscriptionsValues() {
        Subscription basicSub = new Subscription();
        basicSub.setSubscriptionType("basic");
        basicSub.setSubscriptionPrice(50);
        Subscription familySub = new Subscription();
        familySub.setSubscriptionType("family");
        familySub.setSubscriptionPrice(70);
        Subscription premiumSub = new Subscription();
        premiumSub.setSubscriptionType("premium");
        premiumSub.setSubscriptionPrice(100);
        this.subscriptions.put("basic", basicSub);
        this.subscriptions.put("family", familySub);
        this.subscriptions.put("premium", premiumSub);
    }

    public int getOverallBalance() {
        return overallBalance;
    }

    public void setOverallBalance(int overallBalance) {
        this.overallBalance = overallBalance;
    }

    public void addDistributor() {
        this.distributorsToAdd += 1;
    }
    public void addClient(){
        this.clientsToAdd +=1;
    }
    public void addProduct(){
        this.productsToAdd+=1;
    }
    public int getDistributorsToAdd() {
        return distributorsToAdd;
    }

    public void setDistributorsToAdd(int distributorsToAdd) {
        this.distributorsToAdd = distributorsToAdd;
    }

    public int getMonthlyBalance() {
        return monthlyBalance;
    }

    public void setMonthlyBalance(int monthlyBalance) {
        this.monthlyBalance = monthlyBalance;
    }

    public Map<String, Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Map<String, Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void setBasicSubscriptionPrice(int value) {
        Subscription s = subscriptions.get("basic");
        s.setSubscriptionPrice(value);
        this.subscriptions.replace("basic", s);
    }

    public void setFamliySubscriptionPrice(int value) {
        Subscription s = subscriptions.get("family");
        s.setSubscriptionPrice(value);
        this.subscriptions.replace("family", s);
    }

    public void setPremiumSubscriptionPrice(int value) {
        Subscription s = subscriptions.get("premium");
        s.setSubscriptionPrice(value);
        this.subscriptions.replace("premium", s);
    }

    public Subscription getBasicSubscription() {
        return this.subscriptions.get("basic");
    }

    public Subscription getFamliySubscription() {
        return this.subscriptions.get("family");
    }

    public Subscription getPremiumSubscription() {
        return this.subscriptions.get("premium");
    }

    public int getBasicSubscriptionPrice() {
        return this.subscriptions.get("basic").getSubscriptionPrice();
    }

    public int getFamliySubscriptionPrice() {
        return this.subscriptions.get("family").getSubscriptionPrice();
    }

    public int getPremiumSubscriptionPrice() {
        return this.subscriptions.get("premium").getSubscriptionPrice();
    }

    public boolean isEndOfSimulation() {
        return endOfSimulation;
    }

    public int getClientsToAdd() {
        return clientsToAdd;
    }

    public void setClientsToAdd(int clientsToAdd) {
        this.clientsToAdd = clientsToAdd;
    }

    public int getProductsToAdd() {
        return productsToAdd;
    }

    public void setProductsToAdd(int productsToAdd) {
        this.productsToAdd = productsToAdd;
    }

    public int getMonthsOfRowWithLoss() {
        return monthsOfRowWithLoss;
    }

    public void setMonthsOfRowWithLoss(int monthsOfRowWithLoss) {
        this.monthsOfRowWithLoss = monthsOfRowWithLoss;
    }

    public void setEndOfSimulation(boolean endOfSimulation) {
        this.endOfSimulation = endOfSimulation;
    }
}
