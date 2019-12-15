package com.szopolska.ClientAgents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

import java.util.*;

public class ClientAgent extends Agent{


    protected void setup() {
        System.out.println("Hello! Client-agent "+getAID().getName() + " is ready.");
        ClientLocalizatorAggregator.getInstance().setClientLocalizator(this.getAID(), getRandomLocalizator());
        addBehaviour(new ChangeLocalizatorBehaviour(this, 10000));
        addBehaviour(new SendLocalizationBehavior(this, 2000));

    }

    protected void takeDown() {
        System.out.println("Client-agent " +getAID().getName() + " is dead");
    }


    private List<AID> discoverLocalizatorAgents() {
        ArrayList<AID> agentsId = new ArrayList<AID>();
        AMSAgentDescription[] agents = null;

        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults ((long) -1);
            agents = AMSService.search( this, new AMSAgentDescription (), c );
        }
        catch (Exception e) {

        }
        for (int i=0; i < agents.length; i++){
            AID agentID = agents[i].getName();
            String localName = agentID.getLocalName();
            if(localName.startsWith("localizator")) {
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


