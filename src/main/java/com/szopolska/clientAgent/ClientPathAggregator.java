package com.szopolska.clientAgent;

import com.szopolska.utilities.Path;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class ClientPathAggregator extends CyclicBehaviour {

    public ClientPathAggregator() {
        super();
    }

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                showPath((Path) msg.getContentObject());
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }

    private void showPath(Path path) {
        System.out.println(path);
    }
}
