package com.szopolska.shoppingMall;

import com.szopolska.utilities.Path;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PathSenderBahaviour extends TickerBehaviour {

    public PathSenderBahaviour(Agent a, long period) {
        super(a, period);
    }

    @Override
    protected void onTick() {
        Vector<AID> clients = DataAggregator.getInstance().getAllClients();
        for(AID client : clients){
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(client);
            try {
                msg.setContentObject(getPath(client));
            } catch (IOException e) {
                e.printStackTrace();
            }
            myAgent.send(msg);
        }
    }

    //TODO add this
    private Path getPath(AID aid) {
        Path path = new Path(aid);
        Vector<String> shoppingList = DataAggregator.getInstance().getClientShoppingList(aid);
        HashMap<AID, Vector<String>> shopsStocks = DataAggregator.getInstance().getAllShopStock();
        for(String item : shoppingList) {
            for (Map.Entry<AID, Vector<String>> shop_stock : shopsStocks.entrySet()) {
                if(shop_stock.getValue().contains(item.strip())) {
                    path.addShop(shop_stock.getKey().getLocalName());
                }
            }
        }
        return path;
    }


}
