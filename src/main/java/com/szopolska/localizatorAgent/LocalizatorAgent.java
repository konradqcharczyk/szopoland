package com.szopolska.localizatorAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Vector;


public class LocalizatorAgent extends Agent {

    protected void setup() {
        System.out.println("Hello! Localizator-agent " + getAID().getName() + " is ready.");
        addBehaviour(new GetLocalisationBehavior());
        addBehaviour(new ClientDataProviderBehaviuor(this, 5000));
        addBehaviour(new SimpleBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
                msg.setContent("LOC:" + getLocalization().toString());
                myAgent.send(msg);
            }

            @Override
            public boolean done() {
                return true;
            }
        });
    }

    protected void takeDown() {
        System.out.println("Localizator-agent " + getAID().getName() + " is dead");
    }

    private Vector<Double> getLocalization() {
        Vector<Double> loc = new Vector<Double>();
        loc.add(Math.random() * 100);
        loc.add(Math.random() * 100);
        return loc;
    }
}

