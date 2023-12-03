package cz.cvut.fel.dsv.core;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
public class DsvRemote {
    // Flag which indicates that current node is requesting a permission to enter to CS
    @Setter private Boolean isRequesting = false;
    private String username;
    private Address address;

    @Override
    public String toString() {
        return "[username: " +
                username +
                ", address: [" +
                address +
                "], isRequesting: " +
                isRequesting +
                "]";
    }
}
