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

    public Interface(String interfaceName, String interfaceNr, IP_Address ip_address, Subnet subnet, Router myRouter){
        this.interfaceName = interfaceName;
        this.interfaceNr = interfaceNr;
        this.ip_address = ip_address;
        this.subnet = subnet;
        this.myRouter = myRouter;
    }
}
