package com.szopolska.LocalizatorAgents;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.util.HashMap;
import java.util.Vector;

public class GetLocalisationBehavior extends CyclicBehaviour {

    public GetLocalisationBehavior() {
        super();
    }
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null ) {
            try {
                showAd(msg.getObjectContent().toString());
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        }
        else {
            block();
        }
    }

    private void showAd(String ad) {
        System.out.println("On AdvertismentSpace " + myAgent.getLocalname() + " ad: " + ad);
    }
}
