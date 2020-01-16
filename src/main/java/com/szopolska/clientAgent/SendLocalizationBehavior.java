package com.szopolska.clientAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Collections;
import java.util.Vector;

public class SendLocalizationBehavior extends TickerBehaviour {
    private AID localizator;
    private Vector<String> shoppingList;

    public SendLocalizationBehavior(Agent a, long period) {
        super(a, period);
        shoppingList = createShoppingList();
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        ACLMessage msg_to_remove = new ACLMessage(ACLMessage.INFORM);
        if (this.localizator != null && ClientLocalizatorAggregator.getInstance().getClientLocalizator(myAgent.getAID()) != this.localizator) {
            msg_to_remove.addReceiver(this.localizator);
            msg_to_remove.setContent("LOST_CONNECTION");
            myAgent.send(msg_to_remove);
        }
        this.localizator = ClientLocalizatorAggregator.getInstance().getClientLocalizator(myAgent.getAID());
        msg.addReceiver(this.localizator);
        msg.setContent(getLocalization() + "#" + shoppingList);
        myAgent.send(msg);
    }

    private Vector<Double> getLocalization() {
        Vector<Double> loc = new Vector<Double>();
        loc.add(Math.random() * 100);
        loc.add(Math.random() * 100);
        return loc;
    }


    private Vector<String> createShoppingList() {
        Vector<String> allItems = new Vector<String>();
        Vector<String> selectedItems = new Vector<String>();
        allItems.add("BUTY");
        allItems.add("BLUZA");
        allItems.add("T-SHIRT");
        allItems.add("SPODNIE");
        allItems.add("SKARPETY");
//        Collections.shuffle(allItems);
        selectedItems.add(allItems.get(2));
        selectedItems.add(allItems.get(1));
        return selectedItems;
    }
}
