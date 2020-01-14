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
            switch (msg.getOntology()) {
                case "send-data-ontology":
                    try {
                        System.out.println("Client localization from " + msg.getSender().getLocalName() + " " + msg.getContentObject());
                        DataAggregator.getInstance().addClientLoc(msg.getSender(), (HashMap<AID, String>) msg.getContentObject());
                    } catch (UnreadableException e) {
                        e.printStackTrace();
                    }
                    break;
                case "send-offer-ontology":
                    System.out.println("Shop offer req from " + msg.getSender().getLocalName());
                    DataAggregator.getInstance().addAd(msg.getContent());
                    break;
                case "req-ad-ontology":
                    System.out.println("AdSpace req from " + msg.getSender().getLocalName());

                    ACLMessage msgResp = new ACLMessage(ACLMessage.INFORM);
                    msgResp.addReceiver(msg.getSender());
                    msgResp.setLanguage("English");
                    msgResp.setOntology("req-ad-ontology");
                    msgResp.setContent(DataAggregator.getInstance().getRandomAd());
                    myAgent.send(msgResp);
                    break;
            }
        } else {
            block();
        }
    }
}
