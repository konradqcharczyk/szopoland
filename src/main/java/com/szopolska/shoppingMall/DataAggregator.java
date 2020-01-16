package com.szopolska.shoppingMall;

import jade.core.AID;

import java.lang.reflect.Array;
import java.util.*;

public class DataAggregator {

    private static DataAggregator instance = null;

    public static DataAggregator getInstance() {
        if (instance == null) {
            instance = new DataAggregator();
        }
        return instance;
    }

    static HashMap<AID, String> locLocation = new HashMap<AID, String>();
    static HashMap<AID, String> shopLocation = new HashMap<AID, String>();
    static HashMap<AID, Vector<String>> shopStock = new HashMap<AID, Vector<String>>();

    static HashMap<AID, HashMap<AID, String>> clients_loc = new HashMap<AID, HashMap<AID, String>>();
    static HashMap<AID, String> ads = new HashMap<AID, String>();

    public static void setClientLoc(HashMap<AID, HashMap<AID, String>> clients) {
        clients_loc = clients;
    }

    public static void addClientLoc(AID id, HashMap<AID, String> clients) {
        clients_loc.put(id, clients);
    }

    public static HashMap<AID, HashMap<AID, String>> getClientsLoc() {
        return clients_loc;
    }


    public static void addLocLocalization(AID aid, String loc) {
        locLocation.put(aid, loc);
    }

    public static String getLocLocalization(AID aid) {
        return locLocation.get(aid);
    }

    public static void setShopStock(AID aid, String stock) {
        Vector <String> vector = new Vector<>(Arrays.asList(stock.replace('[', ' ').replace(']', ' ').split(",")));
        Vector <String> toPut = new Vector<String>();
        for(String item: vector) {
            toPut.add(item.strip());
        }
        shopStock.put(aid, toPut);
    }

    public static Vector<String> getShopStock(AID aid) {
        return shopStock.get(aid);
    }

    public static HashMap<AID, Vector<String>>getAllShopStock() {
        return shopStock;
    }


    public static void addShopLocalization(AID aid, String loc) {
        shopLocation.put(aid, loc);
    }

    public static String getShopLocalization(AID aid) {
        return shopLocation.get(aid);
    }

    public static void addAd(AID aid, String ad) {
        ads.put(aid, ad);
    }

    public static String getRandomAd() {
        if (ads.size() != 0) {
            Random generator = new Random();
            Object[] values = ads.values().toArray();
            String randomAd = (String) values[generator.nextInt(values.length)];
            return randomAd;
        }
        else {
            return "NO_ADS_ADDED";
        }
    }

    public static Vector<AID> getAllClients() {
        Vector<AID> toReturn = new Vector<>();
        for (Map.Entry<AID, HashMap<AID, String>> entry : clients_loc.entrySet()) {
            for (Map.Entry<AID, String> loc_user : entry.getValue().entrySet()) {
                toReturn.add(loc_user.getKey());
            }
        }
        return toReturn;
    }

    public static Vector<String> getClientShoppingList(AID aid) {
        for (Map.Entry<AID, HashMap<AID, String>> entry : clients_loc.entrySet()) {
            for (Map.Entry<AID, String> loc_user : entry.getValue().entrySet()) {
                if(loc_user.getKey() == aid) {
                    return new Vector (Arrays.asList(loc_user.getValue().split("#")[1].replace('[', ' ').replace(']', ' ').split(",")));
                }
            }
        }
        return null;
    }

}
