package cz.cvut.fel.dsv.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Remote {

    private Long remoteId;
    private Boolean isRequesting = false;

    public Remote (Long remoteId) {
        this.remoteId = remoteId;
    }


}
