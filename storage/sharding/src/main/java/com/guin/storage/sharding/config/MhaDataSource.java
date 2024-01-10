package com.guin.storage.sharding.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MhaDataSource{
    private String masterName;
    private String slaveName;

    public MhaDataSource() { }

}
