package Frontend;

import Addresses.IP_Address;
import Router.Router;
import Router.Interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;



public class Frontend_CMD {

    //überprüfung von interface namen - muss einzigartig sein
    //überprüfung ob interface nicht schon connected ist



    public static void main(String[] args) {
        Router a = new Router("R1");
        a.addInterface("a 0/0", new IP_Address("10.0.0.1",24));
        a.addInterface("a 0/1", new IP_Address("10.0.0.1",24));

        Router b = new Router("R2");
        b.addInterface("a 0/0", new IP_Address("10.0.0.2",24));

        for (Interface inteface_iterate: a.getInterfaces()){
            System.out.println(inteface_iterate.getInterfaceName());
            System.out.println(inteface_iterate.getIp_address());
        }

        for (Interface inteface_iterate: b.getInterfaces()){
            System.out.println(inteface_iterate.getInterfaceName());
            System.out.println(inteface_iterate.getIp_address());
        }

        System.out.println("test");
        a.getInterface("a 0/0").connectToRouterInterface(b.getInterface("a 0/0"));


    }
}
