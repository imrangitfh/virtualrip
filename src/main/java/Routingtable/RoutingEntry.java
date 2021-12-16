package Routingtable;

import Addresses.IP_Address;
import Addresses.Subnet;

import java.util.Objects;

public class RoutingEntry {

    private Subnet network_id;
    private IP_Address nextHop_IP;
    private Integer metric;

    public RoutingEntry(Subnet network_id, IP_Address nextHop_IP, Integer metric) {
        this.network_id = network_id;
        this.nextHop_IP = nextHop_IP;
        this.metric = metric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingEntry that = (RoutingEntry) o;
        return network_id.equals(that.network_id);
    }

    public boolean equals_exact(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingEntry that = (RoutingEntry) o;
        return network_id.equals(that.network_id) && nextHop_IP.equals(that.nextHop_IP) && metric.equals(that.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(network_id);
    }
}
