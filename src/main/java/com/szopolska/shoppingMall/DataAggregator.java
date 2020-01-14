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
    static Vector<String> ads = new Vector<>();

    public static void setClientLoc(HashMap<AID, HashMap<AID, String>> clients) {
        clients_loc = clients;
    }

    public static void addClientLoc(AID id, HashMap<AID, String> clients) {
        clients_loc.put(id, clients);
    }

    public static HashMap<AID, HashMap<AID, String>> getClientsLoc() {
        return clients_loc;
    }

    public static void addAd(String ad) {
        ads.add(ad);
    }

    public static String getRandomAd() {
        Random r = new Random();
        int uid = r.nextInt(ads.size());
        return ads.get(uid);
    }

}
