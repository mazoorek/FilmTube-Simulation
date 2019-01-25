package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Głowna Klasa w której dzieje się cała symulacja. Mamy tu listy Produktów, Dystrybutorów, Klientów, Bazy:
 * productsDatabase, distributorNamesDatabase, z których losujemy wartości do tworzenia dystrybutorów i produktów.
 * Symulacją jest wątek simulationThread, a licznikiem czasu simulationTimer, który mierzy czas symulacji i podaje aktualną datę.
 * W simulationThread dodawani są dystrybutorzy, klienci i dawane są im odpowiednie flagi w określonym czasie symulacji
 * np dystrybutorowi: releaseProduct na true. Jest tu też liczony aktualny stan konta po każdym dniu symulacji.
 *
 */

public class Main extends Application {
    private static SystemOwner owner = new SystemOwner();
    private static ProductsList productsOnFilmtube = new ProductsList();
    private ArrayList<Product> productsDatabase = new ArrayList<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Distributor> distributors = new ArrayList<>();
    private ArrayList<String> distributorNamesDatabase = new ArrayList<>();
    private static SimulationTimer simulationTimer = new SimulationTimer();

    public static void main(String[] args) {
        launch(args);
    }

    public static ProductsList getProductList() {
        return productsOnFilmtube;
    }

    public static SystemOwner getOwner() {
        return owner;
    }

    public static ArrayList<Client> getClients() {
        return clients;
    }

    public static ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public static SimulationTimer getSimulationTimer() {
        return simulationTimer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainGUI.fxml"));
        primaryStage.setTitle("FilmTube");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
        DatabaseToPickRandomDataFrom.getDatabaseToPickRandomDataFrom(productsDatabase, distributorNamesDatabase);
        Random random = new Random();
        Runnable simulationThread = () -> {
            simulationTimer.start();
            while (!owner.isEndOfSimulation()) {
                for (int i = 0; i < clients.size(); i++) {
                    if (clients.get(i).isToBeDeleted()) {
                        clients.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < productsOnFilmtube.getProductsOnFilmtube().size(); i++) {
                    if (productsOnFilmtube.getProductsOnFilmtube().get(i).isToBeRemoved()) {
                        productsOnFilmtube.remove(i);
                        i--;
                    }
                }
                for (int i = 0; i < distributors.size(); i++) {
                    if (distributors.get(i).isToBeDeleted()) {
                        distributors.remove(i);
                        i--;
                    }
                }
                while (owner.getDistributorsToAdd() > 0) {
                    Distributor distributor = new Distributor(distributorNamesDatabase, simulationTimer, productsDatabase, productsOnFilmtube);
                    Thread thread = new Thread(distributor);
                    thread.setDaemon(true);
                    thread.start();
                    distributors.add(distributor);
                    owner.setDistributorsToAdd(owner.getDistributorsToAdd() - 1);
                }
                if (!distributors.isEmpty()) {
                    while (owner.getProductsToAdd() > 0) {
                        int id = random.nextInt(distributors.size());
                        distributors.get(id).setAmountOfNewProductsOwnerWants(distributors.get(id).getAmountOfNewProductsOwnerWants() + 1);
                        owner.setProductsToAdd(owner.getProductsToAdd() - 1);
                    }
                }
                if (simulationTimer.getStart() + simulationTimer.getDayOfSimulation() * 1000 <= System.currentTimeMillis()) {
                    simulationTimer.updateDate();
                    for (Client c : clients) {
                        c.setGoingToBuy(true);
                        c.setGoingToWatch(true);
                    }
                    if (simulationTimer.getDayOfSimulation() % 3 == 1) {
                        for (Distributor d : distributors) {
                            if (random.nextInt() % 2 == 1) {
                                d.setGoingToRelease(true);
                            }
                        }
                    }
                    int numberOfNewClients = random.nextInt(productsOnFilmtube.getProductsOnFilmtube().size() / 2 + 1) + 1;
                    while (numberOfNewClients > 0 || owner.getClientsToAdd() > 0) {
                        Client client = new Client(productsOnFilmtube.getProductsOnFilmtube(), owner.getSubscriptions(), simulationTimer);
                        Thread thread = new Thread(client);
                        thread.setDaemon(true);
                        thread.start();
                        clients.add(client);
                        if (numberOfNewClients > 0) numberOfNewClients -= 1;
                        else if (owner.getClientsToAdd() > 0) owner.setClientsToAdd(owner.getClientsToAdd() - 1);
                    }
                    if (simulationTimer.getDayOfMonth() == 1) {
                        owner.setMonthlyBalance(0);
                        for (Distributor d : distributors) {
                            if (random.nextInt(3) == 1) {
                                d.negotiateDeal();
                            }
                        }
                    }
                    for (Product p : productsOnFilmtube.getProductsOnFilmtube()) {
                        if (p.getAmountOfNewBuyers() > 0) {
                            owner.setMonthlyBalance(p.getCost() * p.getAmountOfNewBuyers() + owner.getMonthlyBalance());
                            owner.setOverallBalance(p.getCost() * p.getAmountOfNewBuyers() + owner.getOverallBalance());
                            for (Distributor d : distributors) {
                                for (Product dProduct : d.getDistributorsProducts()) {
                                    if (d.getFormOfPayment().equals("single product")) {
                                        if (dProduct.getProductID() == p.getProductID()) {
                                            owner.setMonthlyBalance(owner.getMonthlyBalance() - (d.getAmount() * p.getCost() * p.getAmountOfNewBuyers()) / 100);
                                            owner.setOverallBalance(owner.getOverallBalance() - (d.getAmount() * p.getCost() * p.getAmountOfNewBuyers()) / 100);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (simulationTimer.lastDayOfMonth()) {
                        for (Client c : clients) {
                            if (c.isHasSubscription()) {
                                owner.setMonthlyBalance(c.getSubscriptionValue() + owner.getMonthlyBalance());
                                owner.setOverallBalance(c.getSubscriptionValue() + owner.getOverallBalance());
                            }
                        }
                        for (Distributor d : distributors) {
                            if (d.getFormOfPayment().equals("monthly")) {
                                owner.setMonthlyBalance(owner.getMonthlyBalance() - d.getAmount());
                                owner.setOverallBalance(owner.getOverallBalance() - d.getAmount());
                            }
                        }
                        if (owner.getMonthlyBalance() < 0) {
                            owner.setMonthsOfRowWithLoss(owner.getMonthsOfRowWithLoss() + 1);
                            if (owner.getMonthsOfRowWithLoss() == 3) {
                                owner.setEndOfSimulation(true);
                            }
                        } else {
                            owner.setMonthsOfRowWithLoss(0);
                        }
                        for (Product p : productsOnFilmtube.getProductsOnFilmtube()) {
                            p.addNextMonthViews(p.getViewsInMonth());
                            p.setViewsInMonth(0);
                        }
                    }
                }
            }
        };
        new Thread(simulationThread).start();
    }


}
