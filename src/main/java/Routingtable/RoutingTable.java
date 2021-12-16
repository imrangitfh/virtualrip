package Routingtable;

import java.util.ArrayList;

public class RoutingTable {

    private ArrayList<RoutingEntry> routingtable = new ArrayList<RoutingEntry>();

    public void addRoutingEntry (RoutingEntry routingEntry){
        //vergleich von versch. Routingentries
        routingtable.add(routingEntry);
    }

    public void removeRoutingEntry (RoutingEntry search_routingEntry){
        for (RoutingEntry routingEntry:routingtable) {
            if (routingEntry.equals_exact(search_routingEntry)) {
                routingtable.remove(search_routingEntry);
            }
        }
    }

    public boolean containsRoutingEntry (RoutingEntry search_routingEntry){
        for (RoutingEntry routingEntry:routingtable) {
            if (routingEntry.equals(search_routingEntry)) {
                return true;
            }
        }
        return false;
    }




    public ArrayList<RoutingEntry> getRoutingtable() {
        return routingtable;
    }

}
