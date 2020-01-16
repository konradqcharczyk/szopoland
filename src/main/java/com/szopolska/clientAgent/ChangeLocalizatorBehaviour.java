package com.szopolska.clientAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChangeLocalizatorBehaviour extends TickerBehaviour {

    AID currentLocalizator;

    public ChangeLocalizatorBehaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        if (Math.random() > 0.0) {
            List<AID> list = discoverLocalizatorAgents();
            Random r = new Random();
            int uid = r.nextInt(list.size());
            this.currentLocalizator = list.get(uid);
            ClientLocalizatorAggregator.getInstance().setClientLocalizator(myAgent.getAID(), currentLocalizator);
        }
    }

    private List<AID> discoverLocalizatorAgents() {
        ArrayList<AID> agentsId = new ArrayList<AID>();
        AMSAgentDescription[] agents = null;

        try {
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults((long) -1);
            agents = AMSService.search(myAgent, new AMSAgentDescription(), c);
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
}
