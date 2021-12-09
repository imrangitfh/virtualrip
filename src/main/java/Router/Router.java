package Router;

import Routingtable.RoutingTable;

public class Router {
    private String routerName;
    private RoutingTable routingTable = new RoutingTable();

    public Router(String routerName) {
        this.routerName = routerName;
    }
}
