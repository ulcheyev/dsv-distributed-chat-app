package cz.cvut.fel.dsv.core;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class DsvRemote {
    private Boolean isRequesting = false;
    private String username;
}
