package cz.cvut.fel.dsv.core.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Setter
@Getter
public class Address {
    private static final AtomicLong ID_GEN = new AtomicLong(1823);
    private String hostname;
    private int port;
    private Long id;

    public Address(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public Address(String hostname, int port, long nodeId) {
        this(hostname, port);
        this.id = nodeId;
    }

    public Address(Address addr) {
        this(addr.hostname, addr.port, addr.id);
    }

    public void generateId() {
        int hash = Objects.hash(hostname, port);
        long newId = (((long) hash) << 32) | (hash & 0xFFFFFFFFL) + ID_GEN.incrementAndGet();
        id = (newId < 0) ? -(newId) : newId;
    }

    @Override
    public String toString() {
        return "[Address] host:'" + hostname + "', port:'" + port + "'" + ", id:'" + id + "'";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address casted) {
            return Objects.equals(casted.getHostname(), hostname)
                    && casted.getPort() == port;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + hostname.hashCode();
        result = 31 * result + port;
        return result;
    }

    public String getStringAddress() {
        return this.hostname + ":" + port;
    }
}
