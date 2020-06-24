package com.wcs.live.jparest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.ZonedDateTime;

@Entity
public class Player extends BaseModel {

    private String firstName;
    private String lastName;

    private ZonedDateTime birthDate;

    @Column(name="player_rank", nullable = false)
    private int rank;
    private int wins;
    private int losses;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public ZonedDateTime getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public int getWins() {
        return wins;
    }
    public void setWins(int wins) {
        this.wins = wins;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }

}
