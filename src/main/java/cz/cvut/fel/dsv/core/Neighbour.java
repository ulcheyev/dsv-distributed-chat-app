package cz.cvut.fel.dsv.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Neighbour {
    private Address next;
    private Address nextNext;
    private Address prev;
    private Address leader;

    public Neighbour(Address me) {
        this.next = me;
        this.nextNext = me;
        this.prev = me;
        this.leader = me;
    }

    public Neighbour(Address next, Address nextNext, Address prev, Address leader) {
        this.next = next;
        this.nextNext = nextNext;
        this.prev = prev;
        this.leader = leader;
    }

    @Override
    public String toString() {
        return "Neighbor[next:" + this.next + "; " +
                "nextNext:" + this.nextNext + "; " +
                "prev:" + this.prev + "; " +
                "leader:" + this.leader + "]";
    }
}
