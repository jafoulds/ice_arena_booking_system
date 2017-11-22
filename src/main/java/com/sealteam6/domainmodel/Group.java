package com.sealteam6.domainmodel;

import lombok.Getter;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Getter
@Value
public class Group {

    @Id
    String id;

}
