package cz.cvut.fel.dsv.core.data;

import cz.cvut.fel.dsv.core.data.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DsvNeighbours {
    private Address next;
    private Address nextNext;
    private Address prev;
    private Address leader;

    public DsvNeighbours(Address me) {
        this.next = me;
        this.nextNext = me;
        this.prev = me;
        this.leader = me;
    }

    public DsvNeighbours(Address next, Address nextNext, Address prev, Address leader) {
        this.next = next;
        this.nextNext = nextNext;
        this.prev = prev;
        this.leader = leader;
    }

    @Override
    public String toString() {
        return ("[Neighbour] \n\t\t\tnext:" + next +
                ", \n\t\t\tnextNext:" + nextNext +
                ", \n\t\t\tprev:" + prev +
                ", \n\t\t\tleader:" + leader);
    }
}
