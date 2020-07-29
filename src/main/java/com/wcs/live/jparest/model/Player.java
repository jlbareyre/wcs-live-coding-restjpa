package com.wcs.live.jparest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    @ManyToMany
    @JoinTable(
            name = "team_player",
            joinColumns = @JoinColumn(name = "playerUuid"),
            inverseJoinColumns = @JoinColumn(name = "teamUuid")
    )
    @JsonIgnore
    private Set<Team> teams = new HashSet<>();

    @Transient
    private Set<UUID> teamUuids = new HashSet<>();


    @PostLoad
    @PostPersist
    @PostUpdate
    private void loadTeamsUuids() {
        teamUuids = getTeams()
                .stream()
                .map(p -> p.getUuid())
                .collect(Collectors.toSet());
    }


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
    public Set<Team> getTeams() {
        return teams;
    }
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
    public Set<UUID> getTeamUuids() {
        return teamUuids;
    }
    public void setTeamUuids(Set<UUID> teamUuids) {
        this.teamUuids = teamUuids;
    }
}
