package com.szopolska.advertismentSpace;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdvertismentSpaceAgent extends Agent {

    protected void setup() {
        System.out.println("Hello! AdvertaismentSpace-agent " + getAID().getName() + " is ready.");
        AdSpaceAggregator.getInstance().setAdSpaceLocalizator(this.getAID(), getRandomLocalizator());
        addBehaviour(new AdSpaceReviceAdBehavior());
        addBehaviour(new AdSpaceSendReqBehaviour(this, 10000));
    }

    protected void takeDown() {
        System.out.println("AdvertaismentSpace-agent " + getAID().getName() + " is dead");
    }

    private List<AID> discoverLocalizatorAgents() {
        ArrayList<AID> agentsId = new ArrayList<AID>();
        AMSAgentDescription[] agents = null;

        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults((long) -1);
            agents = AMSService.search(this, new AMSAgentDescription(), c);
        } catch (Exception e) {

        }
        for (int i = 0; i < agents.length; i++) {
            AID agentID = agents[i].getName();
            String localName = agentID.getLocalName();
            if (localName.startsWith("localizator")) {
                agentsId.add(agentID);
            }
        }

        return agentsId;
    }

    private AID getRandomLocalizator() {
        Random r = new Random();
        List<AID> list = discoverLocalizatorAgents();
        return list.get(r.nextInt((list.size())));
    }
}