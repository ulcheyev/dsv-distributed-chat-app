package cz.cvut.fel.dsv.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Remote {

    private String remoteId;
    private Boolean isRequesting = false;

    public Remote (String remoteId) {
        this.remoteId = remoteId;
    }


}
