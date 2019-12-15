package com.szopolska.ShoppingMall;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.HashMap;

public class DataAggregatorBehaviour extends CyclicBehaviour{
    HashMap<AID, String>  clients_loc;
    public DataAggregatorBehaviour() {
        super();
    }
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null) {
            try {
                System.out.println("Client localization from " + msg.getSender().getLocalName() + " " + msg.getContentObject());
                DataAggregator.getInstance().addClientLoc(msg.getSender(), (HashMap<AID, String>) msg.getContentObject());
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
        else {
            block();
        }
    }
}
