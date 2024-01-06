package cz.cvut.fel.dsv.core.data;

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

    public DsvNeighbours(DsvNeighbours dsvNeighbours) {
        this.next = dsvNeighbours.getNext();
        this.nextNext = dsvNeighbours.getNextNext();
        this.prev = dsvNeighbours.getPrev();
        this.leader = dsvNeighbours.getLeader();
    }

    public boolean hasRealNeighbours() {
        return !this.next.equals(this.nextNext) || !this.next.equals(this.prev);
    }

    @Override
    public String toString() {
        return "[Neighbour] \n\t\t\tnext:" + next +
                ", \n\t\t\tnextNext:" + nextNext +
                ", \n\t\t\tprev:" + prev +
                ", \n\t\t\tleader:" + leader;
    }
}
