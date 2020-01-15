package com.szopolska.shoppingMall;

import jade.core.AID;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class DataAggregator {

    private static DataAggregator instance = null;

    public static DataAggregator getInstance() {
        if (instance == null) {
            instance = new DataAggregator();
        }
        return instance;
    }


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

}
