package Router;

import Addresses.IP_Address;
import Addresses.Subnet;

public class Interface {

    //static count for interface names ?

    private String interfaceName;
    private IP_Address ip_address;
    private Subnet subnet;

    private Router myRouter;
    private Interface neighbourInterface;
    private Router neighbourRouter;

    public Interface(String interfaceName,  IP_Address ip_address, Router myRouter){
        this.interfaceName = interfaceName;
        this.ip_address = ip_address;
        this.subnet = ip_address.getNetwork_id();
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

    public Router getRouter() {
        return myRouter;
    }

    public Subnet getSubnet() { return this.subnet; }

    public IP_Address getIp_address() { return this.ip_address; }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void connectToRouterInterface (Interface neighbourInterface){
        if (!getSubnet().equals(neighbourInterface.getSubnet())){
            System.out.println("You cannot connect 2 interfaces in different networks");
            return;
        }else if(ip_address.equals(neighbourInterface.getIp_address())){
            System.out.println("You cannot connect 2 interfaces with the same ip-address");
            return;
        }

        this.neighbourInterface = neighbourInterface;
        this.neighbourRouter = neighbourInterface.getRouter();

        neighbourInterface.setNeighbourInterface(this);
        neighbourInterface.setNeighbourRouter(myRouter);

        System.out.println("successful connected");
        System.out.println(neighbourInterface.getNeighbourRouter().getRouterName() + " is connected to " + this.neighbourRouter.getRouterName());
    }

}
