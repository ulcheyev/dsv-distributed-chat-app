package cz.cvut.fel.dsv.core;

public class Mapper {

    private Mapper() {
    }

    public static Address genAddrToAddr(generated.Address gen) {
        return new Address(gen.getHostname(), gen.getPort());
    }

    public static generated.Address addrToGenAddr(Address address) {
        return generated.Address.newBuilder()
                .setHostname(address.getHostname())
                .setPort(address.getPort())
                .build();
    }

    public static Neighbour genNeiToNei(generated.Neighbour gen) {
        return new Neighbour(
                genAddrToAddr(gen.getNext()),
                genAddrToAddr(gen.getNextNext()),
                genAddrToAddr(gen.getPrev()),
                genAddrToAddr(gen.getLeader())
        );
    }

    public static generated.Neighbour neiToGenNei(Neighbour neighbour) {
        return generated.Neighbour.newBuilder()
                .setNext(addrToGenAddr(neighbour.getNext()))
                .setNextNext(addrToGenAddr(neighbour.getNextNext()))
                .setPrev(addrToGenAddr(neighbour.getPrev()))
                .setLeader(addrToGenAddr(neighbour.getLeader()))
                .build();
    }
}
