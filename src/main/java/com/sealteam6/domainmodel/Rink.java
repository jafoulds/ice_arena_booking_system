package com.sealteam6.domainmodel;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Rink {

    @Id
    public String id;

    Rink() {}
}
