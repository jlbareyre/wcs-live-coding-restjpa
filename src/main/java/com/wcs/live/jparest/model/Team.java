package com.wcs.live.jparest.model;


import javax.persistence.Entity;

@Entity
public class Team extends BaseModel {

    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
