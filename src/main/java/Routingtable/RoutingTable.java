package Routingtable;

import java.util.ArrayList;

public class RoutingTable {

    private ArrayList<RoutingEntry> routingtable = new ArrayList<RoutingEntry>();

    public void addRoutingEntry (RoutingEntry newroutingEntry){
        boolean contains = false;
        if(newroutingEntry.getMetric() > 15){
            return;
        }

        for(RoutingEntry entry:routingtable){
            if(entry.getNetwork_id().equals(newroutingEntry.getNetwork_id()) && entry.getMetric() > newroutingEntry.getMetric()){
                routingtable.remove(entry);
                routingtable.add(newroutingEntry);
                contains = true;
                break;
            }
            if(entry.getNetwork_id().equals(newroutingEntry.getNetwork_id())
                    && entry.getMetric() < newroutingEntry.getMetric()
                    && entry.getNextHop_IP().equals(newroutingEntry.getNextHop_IP())){
                routingtable.remove(entry);
                routingtable.add(newroutingEntry);
                contains = true;
                break;
            }
        }
        if(!contains && !routingtable.contains(newroutingEntry)){
            routingtable.add(newroutingEntry);
        }

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


    @Override
    public String toString() {
        String strOutput = "Routingtable [";
        for (int i = 0; i < routingtable.size(); i++) {
            strOutput += routingtable.get(i) + ", \n              ";
        }
        strOutput = strOutput + "]";
        return strOutput;
    }


    public ArrayList<RoutingEntry> getRoutingtable() {
        return routingtable;
    }

}
