package com.szopolska.localizatorAgent;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class GetLocalisationBehavior extends CyclicBehaviour {

    public GetLocalisationBehavior() {
        super();
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            if (msg.getContent().equals("LOST_CONNECTION")) {
                LocalizatorAggregator.getInstance().removeLocalizatorData(myAgent.getAID(), msg.getSender());
            } else {
                LocalizatorAggregator.getInstance().addLocalizatorData(myAgent.getAID(), msg.getSender(), msg.getContent());
            }
        } else {
            block();
        }
    }
}
