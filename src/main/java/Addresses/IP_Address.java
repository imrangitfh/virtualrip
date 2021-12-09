package Addresses;

import java.lang.reflect.Array;

public class IP_Address {


    private int ip_address;
    private Subnet network_id;
    private int snm;


    public IP_Address(String ip_address, int snm){
        String[] quads = ip_address.split("\\.");

        if (quads.length != 4) {
            throw new IllegalArgumentException("ill formed ip - more or less than 4 quads");
        }

        if(Integer.parseInt(quads[0]) > 254 || Integer.parseInt(quads[1]) > 254 || Integer.parseInt(quads[2]) > 254 || Integer.parseInt(quads[3]) > 254) {
            throw new IllegalArgumentException("ill formed ip - an quad is over 254");
        }

        if(Integer.parseInt(quads[0]) <= 0 || Integer.parseInt(quads[3]) <= 0) {
            throw new IllegalArgumentException("ill formed ip - first or last quad is 0");
        }

        this.ip_address = (Integer.parseInt(quads[0]) << 24) + (Integer.parseInt(quads[1]) << 16) + (Integer.parseInt(quads[2]) << 8) + (Integer.parseInt(quads[3]));

        this.snm = snm;

        this.network_id = new Subnet();

    }


    @Override
    public String toString() {
        int a0 = (ip_address       ) & 0xff;
        int a1 = (ip_address >>>  8) & 0xff;
        int a2 = (ip_address >>> 16) & 0xff;
        int a3 = (ip_address >>> 24) & 0xff;

        return "IPAddress [" + a3 + "." + a2 + "." + a1 + "." + a0 + "]";
    }


    public static void main(String[] args) {
        IP_Address ip = new IP_Address("192.168.1.24",24);
        System.out.println(ip.toString());
    }





}
