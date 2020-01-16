package com.szopolska.shopAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Collections;
import java.util.Vector;

public class ShopAgent extends Agent {



    protected void setup() {
        System.out.println("Hello! Shop-agent " + getAID().getName() + " is ready.");
        addBehaviour(new MessageReceiver());
        addBehaviour(new OfferProvider(this, 50000));

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
        addBehaviour(new SimpleBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
                msg.setContent("STK:" + createStockList().toString());
                myAgent.send(msg);
            }

            @Override
            public boolean done() {
                return true;
            }
        });
    }

    protected void takeDown() {
        System.out.println("Shop-agent " + getAID().getName() + " is dead");
    }

    private Vector<Double> getLocalization() {
        Vector<Double> loc = new Vector<Double>();
        loc.add(Math.random() * 100);
        loc.add(Math.random() * 100);
        return loc;
    }

    private Vector<String> createStockList() {
        Vector<String> allItems = new Vector<String>();
        Vector<String> selectedItems = new Vector<String>();
        allItems.add("BUTY");
        allItems.add("BLUZA");
        allItems.add("T-SHIRT");
        allItems.add("SPODNIE");
        allItems.add("SKARPETY");
//        Collections.shuffle(allItems);
        selectedItems.add(allItems.get(0));
        selectedItems.add(allItems.get(1));
        selectedItems.add(allItems.get(2));
        return selectedItems;
    }
}
