package cz.cvut.fel.dsv.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;
import java.util.Objects;

@Setter
@Getter
public class Address {

    private String hostname;
    private int port;
    private Long id;

    public Address (String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    private Address(){}

    public Address (Address addr) {
        this(addr.hostname, addr.port);
    }

    public Address(String hostname, int port, long nodeId) {
        this(hostname, port);
        this.id = nodeId;
    }


    @Override
    public String toString() {
        return("Address [host:'"+hostname+"', port:'"+port+"']");
    }

    @Override
    public boolean equals(Object obj) {
        assert obj instanceof Address;
        Address casted = (Address) obj;
        return Objects.equals(casted.getHostname(), hostname) && casted.getPort() == port;
    }

    public void generateId() {
        int hash = Objects.hash(hostname, port);
        id = (((long) hash) << 32) | (hash & 0xFFFFFFFFL);
    }
}
