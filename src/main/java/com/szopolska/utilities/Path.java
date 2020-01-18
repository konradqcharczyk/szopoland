package com.szopolska.utilities;

import jade.core.AID;

import java.io.Serializable;
import java.util.Vector;

public class Path implements Serializable {
    AID clientAid;
    Vector<String> shops;
    public Path(AID clientAid) {
        this.clientAid = clientAid;
        this.shops = new Vector<>();
    }
    public String toString() {
        return "Path for " + clientAid.getLocalName() + ": " + shops;
    }
    public void addShop(String shopName) {
        if(!shops.contains(shopName)) {
            shops.add(shopName);
        }
    }
}
