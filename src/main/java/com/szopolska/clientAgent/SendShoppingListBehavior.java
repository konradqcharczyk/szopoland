package com.szopolska.clientAgent;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.util.Collections;
import java.util.Vector;

public class SendShoppingListBehavior extends Behaviour {
    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("localizator01", AID.ISLOCALNAME));
        msg.setContent(CreateShoppingList() + "");
        myAgent.send(msg);
    }

    @Override
    public boolean done() {
        return true;
    }

    private Vector<String> CreateShoppingList() {
        Vector<String> allItems = new Vector<String>();
        Vector<String> selectedItems = new Vector<String>();
        allItems.add("BUTY");
        allItems.add("BLUZA");
        allItems.add("T-SHIRT");
        allItems.add("SPODNIE");
        allItems.add("SKARPETY");
        Collections.shuffle(allItems);
        selectedItems.add(allItems.get(0));
        selectedItems.add(allItems.get(1));
        return selectedItems;
    }
}
