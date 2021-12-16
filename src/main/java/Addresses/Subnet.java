package Addresses;

public class Subnet {
    private Integer snm;
    private IP_Address network_id;

    public Subnet(IP_Address ip_address) {
        int ip_add_int = ip_address.getIp_address_int();
        this.snm = ip_address.get_SNM();
        ip_add_int = ip_add_int >> 32-snm;
        ip_add_int = ip_add_int << 32-snm;

        network_id = ip_address;
        network_id.setIp_address_int(ip_add_int);

    }

    @Override
    public String toString() {
        return network_id.toString();
    }

    public IP_Address get_BroadcastIP(){
        IP_Address broadcast = network_id;
        broadcast.setIp_address_int(network_id.getIp_address_int() | getSetBits(32-snm));
        return broadcast;
    }

    public int getSetBits (int count) {
        int result = 0;
        int combinder =1;
        for(int i= 0;i<count;i++){
            result += combinder;
            combinder = combinder << 1;
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subnet subnet = (Subnet) o;
        return snm.equals(subnet.snm) && network_id.equals(subnet.network_id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static void main(String[] args) {
        IP_Address ip = new IP_Address("10.10.10.1",24);
        Subnet netid = new Subnet(ip);

        IP_Address ip1 = new IP_Address("10.10.11.1",24);
        Subnet netid1 = new Subnet(ip1);

        System.out.println(netid.equals(netid1));
    }
}
