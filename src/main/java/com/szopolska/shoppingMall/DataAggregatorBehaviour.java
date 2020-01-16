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
            if (msg.getContent().substring(0,3).equals("LOC")) {
                System.out.println(msg.getSender().getLocalName() + " localization " +  ": " + msg.getContent().substring(4));
                switch (msg.getSender().getName().substring(0,3)) {
                    case "loc":
                        DataAggregator.getInstance().addLocLocalization(msg.getSender(), msg.getContent().substring(4));
                        break;
                    case "sho":
                        DataAggregator.getInstance().addShopLocalization(msg.getSender(), msg.getContent().substring(4));
                        break;
                }
            }
            else {
                switch (msg.getSender().getName().substring(0, 3)) {
                    case "loc":
                        try {
                            System.out.print("Client data from " + msg.getSender().getLocalName() + ": ");
                            for (AID key : ((HashMap<AID, String>) msg.getContentObject()).keySet()) {
                                System.out.print(key.getLocalName() + " with data: " + ((HashMap<AID, String>) msg.getContentObject()).get(key) + ", ");
                            }
                            System.out.println();
                            DataAggregator.getInstance().addClientLoc(msg.getSender(), (HashMap<AID, String>) msg.getContentObject());
                        } catch (UnreadableException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "sho":
                        if (msg.getContent().substring(0,3).equals("OFF")) {
                            System.out.println("Shop offer from " + msg.getSender().getLocalName() + ": " + msg.getContent().substring(4));
                            DataAggregator.getInstance().addAd(msg.getSender(), msg.getContent().substring(4));
                        }
                        else if (msg.getContent().substring(0,3).equals("STK")) {
                            System.out.println("Shop " + msg.getSender().getLocalName() + " offers: " + msg.getContent().substring(4));
                            DataAggregator.getInstance().setShopStock(msg.getSender(), msg.getContent().substring((4)));
                        }

                        break;
                    case "adS":
                        System.out.println("AdSpace request from " + msg.getSender().getLocalName());
                        ACLMessage msgResp = new ACLMessage(ACLMessage.INFORM);
                        msgResp.addReceiver(msg.getSender());
                        msgResp.setContent(DataAggregator.getInstance().getRandomAd());
                        myAgent.send(msgResp);
                        break;
                }
            }
        } else {
            block();
        }
    }
}
