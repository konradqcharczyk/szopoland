package com.szopolska.shopAgent;

import jade.core.Agent;

public class ShopAgent extends Agent {

    protected void setup() {
        System.out.println("Hello! Shop-agent " + getAID().getName() + " is ready.");
        addBehaviour(new MessageReceiver());
        addBehaviour(new OfferProvider(this, 5000));
    }

    protected void takeDown() {
        System.out.println("Shop-agent " + getAID().getName() + " is dead");
    }
}
