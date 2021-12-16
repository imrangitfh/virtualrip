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


    public static void main(String[] args) {
        IP_Address ip = new IP_Address("10.10.10.1",22);
        Subnet netid = new Subnet(ip);

        System.out.println(netid);
    }
}
