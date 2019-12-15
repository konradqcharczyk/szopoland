package com.szopolska.LocalizatorAgents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.HashMap;
import java.util.Vector;

public class GetLocalisationBehavior extends CyclicBehaviour {

    public GetLocalisationBehavior() {
        super();
    }
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null ) {
            if(msg.getContent().equals("LOST_CONNECTION")) {
                LocalizatorAggregator.getInstance().removeLocalizatorData(myAgent.getAID(), msg.getSender());
//                System.out.println("PO WYWALENIU" + LocalizatorAggregator.getInstance().getLocalizatorData(myAgent.getAID()));
            }
            else {
                LocalizatorAggregator.getInstance().addLocalizatorData(myAgent.getAID(), msg.getSender(), msg.getContent());
//                System.out.println("PO DODANIU DO " + myAgent.getLocalName() + " " + LocalizatorAggregator.getInstance().getLocalizatorData(myAgent.getAID()));
            }
        }
        else {
            block();
        }
    }
}
