package Frontend;

import Addresses.IP_Address;
import Addresses.Subnet;
import Router.Router;
import Router.Interface;
import Routingtable.RoutingEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class Frontend_CMD {
    Scanner sc = new Scanner(System.in);
    ArrayList<Router> routers = new ArrayList<Router>();

    public void printIntro(){
        System.out.println("Welcome to RVR - RIP Virtual Routing");
        printGuideline();
    }

    public void printGuideline (){
        System.out.println("You have the following options to continue with our software. \nIf you want to processd with one of the steps, type in the command and press enter. \n\nYou can use the following commands: \n"
        + "createRouter \n" +
                "createInterface \n" +
                "printRoutingtable \n" +
                "printRouters \n" +
                        "printInterfaces \n" +
                        "connectRouters \n" +
                        "getNeighbors \n" +
                        "sendRoutingUpdate \n" +
                        "enableRoutingUpdates \n" +
                        "disableRoutingUpdates \n" +
                "ping \n" +
                "traceroute \n" +
                "help \n" +
                "end \n" +
                "type the 'help' command at any time to display this guidlines again!");
    }

    public void process () throws InterruptedException {

        printIntro();

        routers.add(new Router("R1"));
        routers.get(0).addInterface("int1",new IP_Address("10.0.0.1",24));

        routers.add(new Router("R2"));
        routers.get(1).addInterface("int1",new IP_Address("10.0.0.2",24));
        routers.get(1).addInterface("int2",new IP_Address("11.0.0.1",24));

        routers.add(new Router("R3"));
        routers.get(2).addInterface("int1",new IP_Address("11.0.0.2",24));
        routers.get(2).addInterface("int2",new IP_Address("12.0.0.1",24));

        routers.add(new Router("R4"));
        routers.get(3).addInterface("int1",new IP_Address("12.0.0.2",24));
        routers.get(3).addInterface("int2",new IP_Address("13.0.0.1",24));

        routers.add(new Router("R5"));
        routers.get(4).addInterface("int1",new IP_Address("13.0.0.2",24));


        routers.get(0).getInterfaces().get(0).connectToRouterInterface(routers.get(1).getInterfaces().get(0));
        routers.get(1).getInterfaces().get(1).connectToRouterInterface(routers.get(2).getInterfaces().get(0));
        routers.get(2).getInterfaces().get(1).connectToRouterInterface(routers.get(3).getInterfaces().get(0));
        routers.get(3).getInterfaces().get(1).connectToRouterInterface(routers.get(4).getInterfaces().get(0));



        while (true){
            // help /


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
            }else if(in.equals("sendRoutingUpdate")){
                sendRoutingUpdates();
            }else if(in.equals("enableRoutingUpdates")){
                Router.setAutomaticUpdates(true);
                t1.start();
            }else if(in.equals("disableRoutingUpdates")){
                Router.setAutomaticUpdates(false);
                t1.stop();
            }else if(in.equals("ping")){
                ping_trace(0);
            }else if(in.equals("traceroute")){
                ping_trace(1);
            }
            else if(in.equals("end")){
                return;
            }
            else if(in.equals("help")){
                printGuideline();
            }

        }



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
                    System.out.print(interf.getInterfaceName() + " - ");
                    if(interf.isConnected()){
                        System.out.println("connected to " + interf.getNeighbourRouter().getRouterName());
                    }else {
                        System.out.println("not connected");
                    }
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
                for(Interface interface1:router.getInterfaces()){
                    if(intname.equals(interface1.getInterfaceName())){
                        System.out.println("Dieser Interfacename ist schon vorhanden");
                        return;
                    }
                }
                System.out.print("IP-Address of the interface:");
                String ipadd = sc.next();
                System.out.print("SNM of the interface in digits:");
                int snm = sc.nextInt();

                router.addInterface(intname,new IP_Address(ipadd,snm));
                return;
            }
        }
        System.out.println("Router not found");
    }

    public void connectRouters(){
        System.out.print("name of first Router to connect:");
        String r1 = sc.next();
        System.out.print("Interface of " + r1 +":");
        String int1 = sc.next();
        System.out.print("name of second Router to connect:");
        String r2 = sc.next();
        System.out.print("Interface of " + r2 +":");
        String int2 = sc.next();

        for(Router router1:routers) {
            if (r1.equals(router1.getRouterName())) {
                for(Interface interface1:router1.getInterfaces()){
                    if(interface1.getInterfaceName().equals(int1)){
                        for(Router router2:routers) {
                            if (r2.equals(router2.getRouterName())) {
                                for(Interface interface2:router2.getInterfaces()){
                                    if(interface2.getInterfaceName().equals(int2)){
                                        if(interface1.isConnected() || interface2.isConnected()){
                                            System.out.println("one of the interfaces is already connected");
                                            return;
                                        }
                                        interface1.connectToRouterInterface(interface2);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("a Router or Interface couldn't be found");
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

    public void ping_trace(int a) throws InterruptedException {
        System.out.print("From which host:");
        String host = sc.next();
        System.out.print("To which Destination Host-IP:");
        String dest = sc.next();
        IP_Address destination = new IP_Address(dest,24);
        for(Router router1:routers) {
            if (host.equals(router1.getRouterName())) {
                for(RoutingEntry routingEntry:router1.getRoutingTable().getRoutingtable()){
                    if(routingEntry.getNetwork_id().containsIP(destination)){
                        destination.set_SNM(routingEntry.getNetwork_id().get_SNM());
                    }
                }
                if(a==0){
                    router1.executePing(destination);
                }else {
                    router1.traceroute(destination);
                }


            }
        }
    }





    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while(Router.isAutomaticUpdates()){
                    sendRoutingUpdates();
                    System.out.println("Update sent");
                    TimeUnit.MILLISECONDS.sleep(30000);
                }
            } catch (Exception e) {

            }
        }
    });



    public static void main(String[] args) throws InterruptedException {
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
        c.addInterface("a 0/1", new IP_Address("12.0.0.1",24));

        Router d = new Router("R4");
        d.addInterface("a 0/0", new IP_Address("12.0.0.2",24));
        d.addInterface("a 0/1", new IP_Address("13.0.0.1",24));

        Router e = new Router("R5");
        e.addInterface("a 0/0", new IP_Address("13.0.0.2",24));




        a.getInterface("a 0/0").connectToRouterInterface(b.getInterface("a 0/0"));
        b.getInterface("a 0/1").connectToRouterInterface(c.getInterface("a 0/0"));
        c.getInterface("a 0/1").connectToRouterInterface(d.getInterface("a 0/0"));
        d.getInterface("a 0/1").connectToRouterInterface(e.getInterface("a 0/0"));

        for(int i=0;i<6;i++){
            a.sendRoutingUpdate();
            b.sendRoutingUpdate();
            c.sendRoutingUpdate();
            d.sendRoutingUpdate();
            e.sendRoutingUpdate();
        }

        a.traceroute(new IP_Address("13.0.0.5",24));
        */



        /*
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
