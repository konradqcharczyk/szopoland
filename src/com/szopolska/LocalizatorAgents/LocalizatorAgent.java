package com.szopolska.LocalizatorAgents;
import jade.core.Agent;


public class LocalizatorAgent extends Agent{

    protected void setup() {
        System.out.println("Hello! Localizator-agent "+getAID().getName() + " is ready.");
        addBehaviour(new GetLocalisationBehavior());
        addBehaviour(new ClientDataProviderBehaviuor(this, 5000));
    }

    protected void takeDown() {
        System.out.println("Localizator-agent " +getAID().getName() + " is dead");
    }

}

