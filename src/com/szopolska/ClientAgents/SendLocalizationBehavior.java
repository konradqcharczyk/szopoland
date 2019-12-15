package com.szopolska.ClientAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Vector;

public class SendLocalizationBehavior extends TickerBehaviour {
    AID localizator;
    public SendLocalizationBehavior(Agent a, long period) {
        super(a, period);

    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ACLMessage msg_to_remove = new ACLMessage(ACLMessage.INFORM);
        if (this.localizator != null && ClientLocalizatorAggregator.getInstance().getClientLocalizator(myAgent.getAID()) != this.localizator) {
            msg_to_remove.addReceiver(this.localizator);
            msg_to_remove.setLanguage("English");
            msg_to_remove.setOntology("send-data-ontology");
            msg_to_remove.setContent("LOST_CONNECTION");
//            System.out.println(myAgent.getLocalName() + " Usuwam się z " + this.localizator.getLocalName());
            myAgent.send(msg_to_remove);
        }
        this.localizator = ClientLocalizatorAggregator.getInstance().getClientLocalizator(myAgent.getAID());
        msg.addReceiver(this.localizator);
        msg.setLanguage("English");
        msg.setOntology("send-data-ontology");
        msg.setContent(GetLocalization() + "");
//        System.out.println(myAgent.getLocalName() + " Dodaje się do " + this.localizator.getLocalName());
        myAgent.send(msg);
    }

    private Vector<Double> GetLocalization() {
        Vector<Double> loc = new Vector<Double>();
        loc.add(Math.random() * 100);
        loc.add(Math.random() * 100);
        return loc;
    }
}
