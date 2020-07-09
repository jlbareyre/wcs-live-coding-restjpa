package com.wcs.live.jparest.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AppUser extends BaseModel {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String passwordHash;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
