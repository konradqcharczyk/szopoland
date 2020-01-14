package com.szopolska.advertismentSpace;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class AdSpaceSendReqBehaviour extends TickerBehaviour {

    public AdSpaceSendReqBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("req-ad-ontology");
        try {
            msg.setContentObject(myAgent.getAID());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);
    }
}
