package Frontend;

import Addresses.IP_Address;
import Addresses.Subnet;
import Router.Router;
import Router.Interface;
import Routingtable.RoutingEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;



public class Frontend_CMD {
    Scanner sc = new Scanner(System.in);
    ArrayList<Router> routers = new ArrayList<Router>();

    //überprüfung von interface namen - muss einzigartig sein
    //überprüfung ob interface nicht schon connected ist

    public void printIntro(){
        System.out.println("Welcome to RVR - RIP Virtual Routing");
    }

    public void printGuideline (){
        System.out.println("");
    }

    public void printRouters (){
        for(Router router:routers){
            System.out.println(router.getRouterName());
        }
    }

    public void printInterfaces (){
        System.out.print("Interfaces of which Router?");
        String in = sc.next();
        for(Router router:routers){
            if(in.equals(router.getRouterName())){
                for(Interface interf:router.getInterfaces()){
                    System.out.println(interf.getInterfaceName());
                }
            }
        }
    }

    public void printRoutingtable (){
        System.out.print("Routingtable of which Router?");
        String in = sc.next();
        for(Router router:routers){
            if(in.equals(router.getRouterName())){
                router.printRoutingtable();
            }
        }
    }

    public void createRouter(){
        System.out.print("Name of the Router:");
        String in = sc.next();
        routers.add(new Router(in));
    }

    public void createInterface(){
        System.out.print("Add Interface to which Router:");
        String in = sc.next();
        for(Router router:routers){
            if(in.equals(router.getRouterName())){
                System.out.print("Name of the interface:");
                String intname = sc.next();
                System.out.print("IP-Address of the interface:");
                String ipadd = sc.next();
                System.out.print("SNM of the interface in digits:");
                int snm = sc.nextInt();
                router.addInterface(intname,new IP_Address(ipadd,snm));
                return;
            }
        }
        System.out.println("Interfacename not found");
    }

    public void connectRouters(){
        System.out.print("Router 1 to connect");
        String r1 = sc.next();
        System.out.print("Interface of " + r1 +":");
        String int1 = sc.next();
        System.out.print("Router 2 to connect");
        String r2 = sc.next();
        System.out.print("Interface of " + r2 +":");
        String int2 = sc.next();

        for(Router router1:routers) {
            if (r1.equals(router1.getRouterName())) {
                for(Interface interface1:router1.getInterfaces()){
                    for(Router router2:routers) {
                        if (r2.equals(router2.getRouterName())) {
                            for(Interface interface2:router2.getInterfaces()){
                                interface1.connectToRouterInterface(interface2);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void getNeighbors(){
        System.out.print("Neighbors of which router:");
        String in = sc.next();
        for(Router router1:routers) {
            if (in.equals(router1.getRouterName())) {
                for (Interface interface1 : router1.getInterfaces()) {
                    if(interface1.getNeighbourRouter()!=null){
                        System.out.println("On Interface " + interface1.getInterfaceName() + ": " + interface1.getNeighbourRouter().getRouterName());
                    }
                }
            }
        }
    }

    public void sendRoutingUpdates(){
        for(Router router:routers) {
            router.sendRoutingUpdate();
        }
    }


    public void process (){

        printIntro();
        printGuideline();
        routers.add(new Router("R1"));
        routers.get(0).addInterface("int1",new IP_Address("10.0.0.1",24));
        routers.get(0).addInterface("int2",new IP_Address("11.0.0.1",24));

        routers.add(new Router("R2"));
        routers.get(1).addInterface("int1",new IP_Address("10.0.0.2",24));

        routers.get(0).getInterfaces().get(0).connectToRouterInterface(routers.get(1).getInterfaces().get(0));

        while (true){
            System.out.println("What do you want to do next?");
            String in = sc.next();
            if(in.equals("createRouter")){
                createRouter();
            }else if(in.equals("createInterface")){
                createInterface();
            }else if(in.equals("printRoutingtable")){
                printRoutingtable();
            }else if(in.equals("printInterfaces")){
                printInterfaces();
            }else if(in.equals("printRouters")){
                printRouters();
            }else if(in.equals("connectRouters")){
                connectRouters();
            }else if(in.equals("getNeighbors")){
                getNeighbors();
            }else if(in.equals("sendRoutingUpdates")){
                sendRoutingUpdates();
            }

        }



    }






    public static void main(String[] args) {
        Frontend_CMD cmd = new Frontend_CMD();
        cmd.process();

        /*
        Router a = new Router("R1");
        a.addInterface("a 0/0", new IP_Address("10.0.0.1",24));

        Router b = new Router("R2");
        b.addInterface("a 0/0", new IP_Address("10.0.0.2",24));
        b.addInterface("a 0/1", new IP_Address("11.0.0.2",24));

        Router c = new Router("R3");
        c.addInterface("a 0/0", new IP_Address("11.0.0.1",24));

        a.getInterface("a 0/0").connectToRouterInterface(b.getInterface("a 0/0"));
        b.getInterface("a 0/1").connectToRouterInterface(c.getInterface("a 0/0"));

        a.printRoutingtable();
        b.printRoutingtable();
        c.printRoutingtable();

        b.sendRoutingUpdate();

        System.out.println();

        a.printRoutingtable();
        b.printRoutingtable();
        c.printRoutingtable();


        IP_Address new1 = new IP_Address("10.0.0.2",24);
        IP_Address new2 = new IP_Address("11.0.0.3",24);
        RoutingEntry newr1 = new RoutingEntry(new Subnet(new2 ) ,new1,2);

        a.addRoutingEntry(newr1);

        a.printRoutingtable();

        RoutingEntry newr2 = new RoutingEntry(new Subnet(new2 ) ,new2,1);
        a.addRoutingEntry(newr2);

        a.printRoutingtable();
        */

    }
}
