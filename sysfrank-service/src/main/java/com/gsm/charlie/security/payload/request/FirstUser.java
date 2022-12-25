package com.gsm.charlie.security.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter @Setter
public class FirstUser {
    private String secret;
}
