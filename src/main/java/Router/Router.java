package Router;

import Addresses.IP_Address;
import Addresses.Subnet;
import Routingtable.RoutingTable;

import java.util.ArrayList;

public class Router {
    private String routerName;
    private RoutingTable routingTable = new RoutingTable();
    private ArrayList<Interface> Interfaces = new ArrayList<Interface>();

    public Router(String routerName) {
        this.routerName = routerName;
    }


    public String getRouterName(){return routerName;}

    public ArrayList<Interface> getInterfaces() {
        return Interfaces;
    }

    public void addInterface (String name, IP_Address ip_address){
        Interface new_interface = new Interface(name,ip_address,this);
        Interfaces.add(new_interface);
    }


    public Interface getInterface(String getinterface){
        for(Interface compareInterface:Interfaces){
            if (getinterface.equals(compareInterface.getInterfaceName())){
                System.out.println("interface found");
                return compareInterface;
            }
        }
        System.out.println("No matching interface found");
        return null;
    }


}
