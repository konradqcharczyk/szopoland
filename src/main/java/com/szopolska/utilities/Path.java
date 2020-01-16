package com.szopolska.utilities;

import jade.core.AID;

import java.io.Serializable;

public class Path implements Serializable {
    AID clientAid;
    public Path(AID clientAid) {
        this.clientAid = clientAid;
    }
    public String toString() {
        return "It's path for " + clientAid.getLocalName();
    }
}
