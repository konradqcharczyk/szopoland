package com.szopolska.advertismentSpace;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class AdSpaceReviceAdBehavior extends CyclicBehaviour {

    public AdSpaceReviceAdBehavior() {
        super();
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                showAd(msg.getContentObject().toString());
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }

    private void showAd(String ad) {
        System.out.println("On advertismentSpace " + myAgent.getLocalName() + " ad: " + ad);
    }
}
