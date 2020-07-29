package com.wcs.live.jparest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Team extends BaseModel {

    private String name;


    @ManyToMany
    @JoinTable(
            name = "team_player",
            joinColumns = @JoinColumn(name = "teamUuid"),
            inverseJoinColumns = @JoinColumn(name = "playerUuid")
    )
    @JsonIgnore
    private Set<Player> players = new HashSet<>();

    @Transient
    private Set<UUID> playerUuids = new HashSet<>();

    @PostLoad
    @PostPersist
    @PostUpdate
    private void loadPlayersUuids() {
        playerUuids = getPlayers()
                .stream()
                .map(p -> p.getUuid())
                .collect(Collectors.toSet());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Player> getPlayers() {
        return players;
    }
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    public Set<UUID> getPlayerUuids() {
        return playerUuids;
    }
    public void setPlayerUuids(Set<UUID> playerUuids) {
        this.playerUuids = playerUuids;
    }
}
