package model;

import java.util.ArrayList;

public class ProductsList {
    private ArrayList<Product> productsOnFilmtube = new ArrayList<>();
    private int idOfProduct = 0;
    public synchronized void add(Product product){
        productsOnFilmtube.add(product);
    }
    public synchronized void remove(int id){
        productsOnFilmtube.remove(id);
    }
    public synchronized int getNewId(){
        this.idOfProduct+=1;
        return this.idOfProduct;
    }
    public ArrayList<Product> getProductsOnFilmtube() {
        return productsOnFilmtube;
    }

    public void setProductsOnFilmtube(ArrayList<Product> productsOnFilmtube) {
        this.productsOnFilmtube = productsOnFilmtube;
    }
}
