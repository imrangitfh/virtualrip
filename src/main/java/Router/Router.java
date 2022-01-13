package Router;

import Addresses.IP_Address;
import Addresses.Subnet;
import Routingtable.RoutingEntry;
import Routingtable.RoutingTable;

import java.util.ArrayList;

public class Router {
    private String routerName;
    private RoutingTable routingTable = new RoutingTable();
    private ArrayList<Interface> Interfaces = new ArrayList<Interface>();

    public Router(String routerName) {
        this.routerName = routerName;
    }

    public RoutingTable getRoutingTable() {
        return routingTable;
    }

    public String getRouterName(){return routerName;}

    public ArrayList<Interface> getInterfaces() {
        return Interfaces;
    }

    public void addInterface (String name, IP_Address ip_address){
        Interface new_interface = new Interface(name,ip_address,this);
        Interfaces.add(new_interface);
        routingTable.addRoutingEntry(new RoutingEntry(ip_address.getNetwork_id(), ip_address,0));
    }


    public Interface getInterface(String getinterface){
        for(Interface compareInterface:Interfaces){
            if (getinterface.equals(compareInterface.getInterfaceName())){
                return compareInterface;
            }
        }
        System.out.println("No matching interface found");
        return null;
    }

    public void printRoutingtable(){
        System.out.println(routerName + ":\n" + routingTable);
    }

    public void addRoutingEntry(RoutingEntry entry){
        routingTable.addRoutingEntry(entry);
    }

    public void sendRoutingUpdate(){
        for(Interface int_parse:Interfaces){
            if(int_parse.getNeighbourRouter() != null) {
                for(RoutingEntry entry:routingTable.getRoutingtable()){
                    int_parse.getNeighbourRouter().addRoutingEntry(new RoutingEntry(entry.getNetwork_id(), int_parse.getIp_address(), entry.getMetric()+1));
                }
            }
        }

    }




}
