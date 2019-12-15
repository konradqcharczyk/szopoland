package com.szopolska.ShopAgent;

import com.szopolska.LocalizatorAgents.LocalizatorAggregator;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class MessageReceiver extends CyclicBehaviour {

    public MessageReceiver() {
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
