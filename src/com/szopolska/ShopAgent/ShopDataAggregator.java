package com.szopolska.ShoppingMall;

import jade.core.AID;

import java.util.HashMap;

public class ShopDataAggregator {

    private static ShopDataAggregator instance = null;

    public static ShopDataAggregator getInstance() {
        if(instance == null) {
            instance = new DataAggregator();
        }
        return instance;
    }

    static HashMap<AID, HashMap<AID, String>> dataFromLocalizators = new HashMap<AID, HashMap<AID, String>>();

    public static void setDataFromLocalizators(HashMap<AID, HashMap<AID, String>>  data) {
        dataFromLocalizators = data;
    }
    public static void addDataFromLocalizators(AID id, HashMap<AID, String> data) {
        dataFromLocalizators.put(id, clients);
    }

    public static HashMap<AID, HashMap<AID, String>>  getClientsLoc() {
        return dataFromLocalizators;
    }

}
