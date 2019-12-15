package com.szopolska.ClientAgents;

import com.szopolska.ShoppingMall.DataAggregator;
import jade.core.AID;

import java.util.HashMap;

public class ClientLocalizatorAggregator {
    private static ClientLocalizatorAggregator instance = null;

    public static ClientLocalizatorAggregator getInstance() {
        if(instance == null) {
            instance = new ClientLocalizatorAggregator();
        }
        return instance;
    }


    private static HashMap<AID, AID> clients_localizator = new HashMap<AID, AID>();

    public static void setClientsLocalizator(HashMap<AID, AID> clients) {
        clients_localizator = clients;
    }

    public static void setClientLocalizator(AID client, AID localizator) {
        clients_localizator.put(client, localizator);
    }

    public static HashMap<AID, AID> getClientsLocalizator() {
        return clients_localizator;
    }

    public static AID getClientLocalizator(AID client) {
        return clients_localizator.get(client);
    }

}
