package com.szopolska.ShoppingMall;

import jade.core.AID;

import java.util.HashMap;

public class DataAggregator {

    private static DataAggregator instance = null;

    public static DataAggregator getInstance() {
        if(instance == null) {
            instance = new DataAggregator();
        }
        return instance;
    }


    static HashMap<AID, HashMap<AID, String>> clients_loc = new HashMap<AID, HashMap<AID, String>>();

    public static void setClientLoc(HashMap<AID, HashMap<AID, String>>  clients) {
        clients_loc = clients;
    }
    public static void addClientLoc(AID id, HashMap<AID, String> clients) {
        clients_loc.put(id, clients);
    }

    public static HashMap<AID, HashMap<AID, String>>  getClientsLoc() {
        return clients_loc;
    }

}
