package com.sealteam6.domainmodel;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
@Setter
public class User {


    @Id
    public String username;
    public String emailAddress;


}
