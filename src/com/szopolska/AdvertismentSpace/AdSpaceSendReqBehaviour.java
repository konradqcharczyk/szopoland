package com.szopolska.LocalizatorAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.HashMap;

public class AdSpaceSendReqBehaviour extends TickerBehaviour{


    public AdSpaceSendReqBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("send-data-ontology");
        try {
            msg.setContentObject(myAgent.getAID());
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);
    }
}
