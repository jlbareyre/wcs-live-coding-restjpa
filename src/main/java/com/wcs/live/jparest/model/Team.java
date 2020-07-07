package com.wcs.live.jparest.model;


import javax.persistence.Entity;

@Entity
public class Team extends BaseModel {

    private String name;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Player> players = new ArrayList<>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public List<Player> getPlayers() {
//        return players;
//    }
//    public void setPlayers(List<Player> players) {
//        this.players = players;
//    }
}
