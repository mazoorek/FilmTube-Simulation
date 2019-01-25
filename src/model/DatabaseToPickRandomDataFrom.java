package model;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa, w której pobierane są jsony z danymi, które są następnie zapisane do bazy produktów z której będą losowane
 * dane/parametry nowych produktów.
 */
public class DatabaseToPickRandomDataFrom {
    public static JSONArray getJsonArray(String file){
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();
        try{
            array = (JSONArray) parser.parse(new FileReader("src\\resources\\"+file));
        }catch(IOException | ParseException e){
            e.printStackTrace();
        }
        return array;
    }
    public static void getDatabaseToPickRandomDataFrom(ArrayList<Product> productsDatabase,ArrayList<String> distributorsDatabase){
        JSONArray jsonDescriptions = getJsonArray("descriptions.json");
        JSONArray jsonDistributors = getJsonArray("distributors.json");
        JSONArray jsonProducts = getJsonArray("productsDatabase.json");
        JSONArray jsonCountries = getJsonArray("countries.json");
        Random generator = new Random();
        ArrayList<String> descriptions =  new ArrayList<>();
        for(Object i: jsonDescriptions){
            JSONObject obj = (JSONObject)i;
            descriptions.add((String)obj.get("description"));
        }
        for(Object i: jsonDistributors){
            JSONObject obj = (JSONObject)i;
            distributorsDatabase.add((String)obj.get("distributor"));
        }
        ArrayList<ArrayList> countriesList = new ArrayList<>();
        for(Object i:jsonCountries){
            ArrayList<String>nationsList = new ArrayList<>();
            JSONObject obj = (JSONObject)i;
            JSONArray nations = (JSONArray)obj.get("countries");
           for(Object n : nations){
                nationsList.add((String)n);
            }
           if(!nationsList.isEmpty())countriesList.add(nationsList);
        }
        for(Object i: jsonProducts){
            Product p = new Product();
            JSONObject obj = (JSONObject)i;
            p.setTitle((String)obj.get("title"));
            p.setYear ((int)(long)obj.get("year"));
            JSONArray jsonCast = (JSONArray)obj.get("cast");
            ArrayList<String>cast = new ArrayList<>();
            for(Object c : jsonCast){
                cast.add((String)c);
            }
            p.setCast(cast);
            JSONArray jsonGenres = (JSONArray)obj.get("genres");
            ArrayList<String>genres = new ArrayList<>();
            for(Object g : jsonGenres){
                genres.add((String)g);
            }
            p.setGenres(genres);
            ArrayList<String>productionCountries = new ArrayList<>();
            ArrayList countriesArray= countriesList.get(generator.nextInt(countriesList.size()-1));;
            for(Object o:countriesArray){
                productionCountries.add((String)o);
            }
            p.setCountriesOfProduction(productionCountries);
            String description = descriptions.get(generator.nextInt(descriptions.size()-1));
            p.setDescription(description);
            productsDatabase.add(p);
        }
    }
}
