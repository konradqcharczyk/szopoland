package com.szopolska.advertismentSpace;

import jade.core.AID;

import java.util.HashMap;

public class AdSpaceAggregator {
    private static AdSpaceAggregator instance = null;

    public static AdSpaceAggregator getInstance() {
        if (instance == null) {
            instance = new AdSpaceAggregator();
        }
        return instance;
    }


    private static HashMap<AID, AID> adSpace_localizator = new HashMap<AID, AID>();

    public static void setAdSpacesLocalizator(HashMap<AID, AID> adSpace_Loc) {
        adSpace_localizator = adSpace_Loc;
    }

    public static void setAdSpaceLocalizator(AID adSpace, AID localizator) {
        adSpace_localizator.put(adSpace, localizator);
    }

    public static HashMap<AID, AID> getClientsLocalizator() {
        return adSpace_localizator;
    }

    public static AID getAdSpaceLocalizator(AID adSpace) {
        return adSpace_localizator.get(adSpace);
    }

}
