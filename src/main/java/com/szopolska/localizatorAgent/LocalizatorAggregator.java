package com.szopolska.localizatorAgent;

import jade.core.AID;

import java.util.HashMap;

public class LocalizatorAggregator {
    private static LocalizatorAggregator instance = null;

    public static LocalizatorAggregator getInstance() {
        if (instance == null) {
            instance = new LocalizatorAggregator();
        }
        return instance;
    }

    static HashMap<AID, HashMap<AID, String>> localizatorData = new HashMap<AID, HashMap<AID, String>>();

    public static void putLocalizatorData(AID id, HashMap<AID, String> data) {
        localizatorData.put(id, data);
    }

    public static void addLocalizatorData(AID id, AID clientId, String clientLoc) {
        if (localizatorData.get(id) != null) {
            localizatorData.get(id).put(clientId, clientLoc);
        } else {
            HashMap<AID, String> clientsLocMap = new HashMap<>();
            clientsLocMap.put(clientId, clientLoc);
            localizatorData.put(id, clientsLocMap);
        }
    }

    public static void removeLocalizatorData(AID id, AID clientId) {
        localizatorData.get(id).remove(clientId);
//        System.out.println(localizatorData);
    }

    public static HashMap<AID, String> getLocalizatorData(AID id) {
        return localizatorData.get(id);
    }
}
