package Routingtable;

import java.util.ArrayList;

public class RoutingTable {

    private ArrayList<RoutingEntry> routingtable = new ArrayList<RoutingEntry>();

    public void addRoutingEntry (RoutingEntry routingEntry){
        //vergleich von versch. Routingentries

        routingtable.add(routingEntry);
    }

    public ArrayList<RoutingEntry> getRoutingtable() {
        return routingtable;
    }

}
