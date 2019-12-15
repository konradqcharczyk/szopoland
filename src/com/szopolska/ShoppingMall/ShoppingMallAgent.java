package com.szopolska.ShoppingMall;

import com.szopolska.LocalizatorAgents.GetLocalisationBehavior;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.HashMap;
import java.util.Vector;

public class ShoppingMallAgent extends Agent {

    protected void setup() {
        System.out.println("Hello! Mall-agent "+getAID().getName() + " is ready.");
        addBehaviour(new DataAggregatorBehaviour());
    }

    protected void takeDown() {
        System.out.println("Mall-agent " +getAID().getName() + " is dead");
    }

}
