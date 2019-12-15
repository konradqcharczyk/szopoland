package com.szopolska.ShopAgent;

import com.szopolska.ClientAgents.ClientLocalizatorAggregator;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class OfferProvider extends TickerBehaviour {


    public OfferProvider(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("mall", AID.ISLOCALNAME));
        msg.setLanguage("English");
        msg.setOntology("send-offer-ontology");
        msg.setContent(provideOffer().varchar);
        myAgent.send(msg);
    }

    private Offer provideOffer() {
        List<Offer> offerList = new LinkedList<>();
        IntStream.range(0, 10).forEach(i -> {
                    int id = new Random().nextInt(100);
                    offerList.add(new Offer(id, "offer" + id));
                }
        );

        return offerList.get(new Random().nextInt(10));
    }

    private static class Offer {
        private int id;
        private String varchar;

        public Offer(int id, String varchar) {
            this.id = id;
            this.varchar = varchar;
        }
    }
}


