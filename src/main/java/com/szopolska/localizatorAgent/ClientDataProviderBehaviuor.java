package com.szopolska.localizatorAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class ClientDataProviderBehaviuor extends TickerBehaviour {


    public ClientDataProviderBehaviuor(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
        try {
            msg.setContent("LOC");
            msg.setContentObject(LocalizatorAggregator.getInstance().getLocalizatorData(myAgent.getAID()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        myAgent.send(msg);
    }
}
