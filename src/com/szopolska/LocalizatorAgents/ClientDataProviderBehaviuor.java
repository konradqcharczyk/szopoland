package com.szopolska.LocalizatorAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.HashMap;

public class ClientDataProviderBehaviuor extends TickerBehaviour{


    public ClientDataProviderBehaviuor(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("send-data-ontology");
        try {
            msg.setContentObject(LocalizatorAggregator.getInstance().getLocalizatorData(myAgent.getAID()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);
    }
}
