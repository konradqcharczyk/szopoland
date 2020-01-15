package com.szopolska.shoppingMall;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.HashMap;

public class DataAggregatorBehaviour extends CyclicBehaviour {
    HashMap<AID, String> clients_loc;

    public DataAggregatorBehaviour() {
        super();
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            switch (msg.getSender().getName().substring(0,3)) {
                case "loc":
                    try {
                        System.out.println("Client localization from " + msg.getSender().getLocalName() + " " + msg.getContentObject());
                        DataAggregator.getInstance().addClientLoc(msg.getSender(), (HashMap<AID, String>) msg.getContentObject());
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                    break;
                case "sho":
                    System.out.println("Shop offer req from " + msg.getSender().getLocalName() + " " + msg.getContent());
                    DataAggregator.getInstance().addAd(msg.getSender(),msg.getContent());
                    break;
                case "adS":
                    System.out.println("AdSpace req from " + msg.getSender().getLocalName());

                    ACLMessage msgResp = new ACLMessage(ACLMessage.INFORM);
                    msgResp.addReceiver(msg.getSender());
                    msgResp.setContent(DataAggregator.getInstance().getRandomAd());
                    myAgent.send(msgResp);
                    break;
            }
        } else {
            block();
        }
    }
}
