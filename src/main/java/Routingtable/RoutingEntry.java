package Routingtable;

import Addresses.IP_Address;
import Addresses.Subnet;

public class RoutingEntry {

    private Subnet network_id;
    private IP_Address nextHop_IP;
    private Integer metric;

    public RoutingEntry(Subnet network_id, IP_Address nextHop_IP, Integer metric) {
        this.network_id = network_id;
        this.nextHop_IP = nextHop_IP;
        this.metric = metric;
    }

}
