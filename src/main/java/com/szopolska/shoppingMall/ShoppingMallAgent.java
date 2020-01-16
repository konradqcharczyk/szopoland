package com.szopolska.shoppingMall;

import jade.core.Agent;

public class ShoppingMallAgent extends Agent {

    protected void setup() {
        System.out.println("Hello! Mall-agent " + getAID().getName() + " is ready.");
        addBehaviour(new DataAggregatorBehaviour());
        addBehaviour(new PathSenderBahaviour(this, 5000));
    }

    protected void takeDown() {
        System.out.println("Mall-agent " + getAID().getName() + " is dead");
    }

}
