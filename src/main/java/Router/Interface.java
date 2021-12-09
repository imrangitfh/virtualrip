package Router;

import Addresses.IP_Address;
import Addresses.Subnet;

public class Interface {
    private String interfaceName;
    private String interfaceNr;
    private IP_Address ip_address;
    private Subnet subnet;

    private Router myRouter;
    private Interface neighbourInterface;
    private Router neighbourRouter;

    public Interface(String interfaceName, String interfaceNr, IP_Address ip_address, Subnet subnet, Router myRouter){
        this.interfaceName = interfaceName;
        this.interfaceNr = interfaceNr;
        this.ip_address = ip_address;
        this.subnet = subnet;
        this.myRouter = myRouter;
    }

    public Interface getNeighbourInterface() {
        return neighbourInterface;
    }

    public void setNeighbourInterface(Interface neighbourInterface) {
        this.neighbourInterface = neighbourInterface;
    }

    public Router getNeighbourRouter() {
        return neighbourRouter;
    }

    public void setNeighbourRouter(Router neighbourRouter) {
        this.neighbourRouter = neighbourRouter;
    }

    public Router getRoutername() {
        return myRouter;
    }


    public void connectToRouter (Interface neighbourInterface){
        //SUBNET ÜBERPRÜFEN BEVOR CONNECT
        this.neighbourInterface = neighbourInterface;
        this.neighbourRouter = neighbourInterface.getRoutername();

        neighbourInterface.setNeighbourInterface(this);
        neighbourInterface.setNeighbourRouter(myRouter);
    }
}
