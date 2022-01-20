package Router;

import Addresses.IP_Address;
import Addresses.Subnet;
import Routingtable.RoutingEntry;
import Routingtable.RoutingTable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Router {
    private static boolean automaticUpdates;
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

    public static boolean isAutomaticUpdates() {
        return automaticUpdates;
    }

    public static void setAutomaticUpdates(boolean automaticUpdates) {
        Router.automaticUpdates = automaticUpdates;
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

    public Interface getOutgoingInterface(Subnet dest_ip_add){
        for (RoutingEntry routingEntry:routingTable.getRoutingtable()){
            if(routingEntry.getNetwork_id().equals(dest_ip_add)){
                for(Interface outinterface:Interfaces){
                    if(outinterface.getSubnet().equals(routingEntry.getNextHop_IP().getNetwork_id())){
                        return outinterface;
                    }
                }
            }
        }
        return null;
    }

    public void executePing(IP_Address dest_ip_address){
        Router currentRouter = this;
        if(routingTable.containsIP(dest_ip_address)){
            while (currentRouter.routingTable.getRoutingEntry(dest_ip_address).getMetric()!=0){
                //System.out.println("From: " + currentRouter.getRouterName() + "\nHops to go: " + currentRouter.routingTable.getRoutingEntry(dest_ip_address).getMetric());
                currentRouter = currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getNeighbourRouter();
            }
            //System.out.println("From: " + currentRouter.getRouterName() + "\nHops to go: " + currentRouter.routingTable.getRoutingEntry(dest_ip_address).getMetric());
            currentRouter = currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getNeighbourRouter();

            for(Interface interface_1:currentRouter.getInterfaces()){
                if(interface_1.getIp_address().equals(dest_ip_address)){
                    System.out.println("Ping successful");
                    return;
                }
            }
            System.out.println("Destination Host unreachable");

        }else {
            System.out.println("Destination Network unreachable");
        }
    }

    public void traceroute(IP_Address dest_ip_address) throws InterruptedException {
        int counter = 1;
        Router currentRouter = this;
        System.out.println("Start traceroute to destination " + dest_ip_address);
        if(routingTable.containsIP(dest_ip_address)){
            while (currentRouter.routingTable.getRoutingEntry(dest_ip_address).getMetric()!=0){
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println(counter + " --- " + currentRouter.getRouterName() + " --- " + currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getIp_address());
                currentRouter = currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getNeighbourRouter();
                counter++;
            }
            TimeUnit.MILLISECONDS.sleep(2000);
            System.out.println(counter + " --- " + currentRouter.getRouterName() + " --- " + currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getIp_address());
            counter++;
            TimeUnit.MILLISECONDS.sleep(2000);
            currentRouter = currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getNeighbourRouter();

            for(Interface interface_1:currentRouter.getInterfaces()){
                if(interface_1.getIp_address().equals(dest_ip_address)){
                    System.out.println(counter + " --- " + currentRouter.getRouterName() + " --- " + currentRouter.getOutgoingInterface(dest_ip_address.getNetwork_id()).getIp_address());
                    System.out.println("Host reached");
                    return;
                }
            }
            System.out.println("Destination Host unreachable");
        }else {
            System.out.println("Destination Network unreachable");
        }
    }







}
