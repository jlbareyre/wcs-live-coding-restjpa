package com.wcs.live.jparest.service;

import com.wcs.live.jparest.dao.BaseDao;
import com.wcs.live.jparest.dao.PlayerDao;
import com.wcs.live.jparest.dao.TeamDao;
import com.wcs.live.jparest.model.Player;
import com.wcs.live.jparest.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeamService extends AbstractBaseService<Team> {

    @Autowired
    private TeamDao teamDao;
    @Autowired
    private PlayerDao playerDao;

    @Override
    public BaseDao<Team> getDao() {
        return teamDao;
    }


    public Team create(Team team) {
        Team updatedTeam = withPlayers(team);
        return super.create(updatedTeam);
    }

    public Team update(Team team) {
        Team updatedTeam = withPlayers(team);
        return super.update(updatedTeam);
    }

    private Team withPlayers(Team team) {

        List<Player> foundPlayers = playerDao.findAllById(team.getPlayerUuids());

        // check that all requested uuids are found
        Set<UUID> foundUuids = foundPlayers.stream()
                .map(Player::getUuid)
                .collect(Collectors.toSet());
        Set<UUID> notfoundUuids = team.getPlayerUuids()
                .stream()
                .filter(uuid -> !foundUuids.contains(uuid))
                .collect(Collectors.toSet());
        if (!notfoundUuids.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not find playersUuids> " + notfoundUuids);

        team.setPlayers(new HashSet<>(foundPlayers));
        return team;
    }
}