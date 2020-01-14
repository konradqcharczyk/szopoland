package com.szopolska.shopAgent;

import jade.core.AID;

import java.util.HashMap;

public class ShopDataAggregator {

    private static ShopDataAggregator instance = null;

    public static ShopDataAggregator getInstance() {
        if (instance == null) {
            instance = new ShopDataAggregator();
        }
        return instance;
    }

    static HashMap<AID, HashMap<AID, String>> dataFromLocalizators = new HashMap<>();

    public static void setDataFromLocalizators(HashMap<AID, HashMap<AID, String>> data) {
        dataFromLocalizators = data;
    }

    public static void addDataFromLocalizators(AID id, HashMap<AID, String> data) {
        HashMap<AID, String> clients = new HashMap<>();
        dataFromLocalizators.put(id, clients);
    }

    public static HashMap<AID, HashMap<AID, String>> getClientsLoc() {
        return dataFromLocalizators;
    }

    public void addLocalizatorData(AID aid, AID sender, String content) {
    }
}
