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


    public void addInterface (String name, String nr, IP_Address ip_address, Integer snm){
        Interface new_interface = new Interface(name,nr,ip_address,new Subnet(),this);
        Interfaces.add(new_interface);
    }





}
