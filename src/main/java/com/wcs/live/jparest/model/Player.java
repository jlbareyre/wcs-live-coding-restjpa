package com.wcs.live.jparest.model;


import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player extends BaseModel {

    private String firstName;
    private String lastName;

    private ZonedDateTime birthDate;

    @Column(name = "player_rank", nullable = false)
    private int rank;
    private int wins;
    private int losses;

    @Embedded
    private Address address;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Achievement> achievements = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "team_id")
//    private Team team;

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

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public List<Achievement> getAchievements() {
        return achievements;
    }
    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }
//    public Team getTeam() {
//        return team;
//    }
//    public void setTeam(Team team) {
//        this.team = team;
//    }
}
