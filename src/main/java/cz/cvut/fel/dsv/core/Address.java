package cz.cvut.fel.dsv.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {
    private String hostname;
    private int port;

    public Address () {
        this("127.0.0.1", 2010);
    }


    public Address (String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }


    public Address (Address addr) {
        this(addr.hostname, addr.port);
    }


    @Override
    public String toString() {
        return("Address [host:'"+hostname+"', port:'"+port+"']");
    }


}
