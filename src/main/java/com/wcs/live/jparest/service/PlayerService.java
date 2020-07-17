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
public class PlayerService extends AbstractBaseService<Player> {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public BaseDao<Player> getDao() {
        return playerDao;
    }


    public Player create(Player player) {
        Player updatedPlayer = withTeams(player);
        return super.create(updatedPlayer);
    }

    public Player update(Player player) {
        Player updatedPlayer = withTeams(player);
        return super.update(updatedPlayer);
    }

    private Player withTeams(Player player) {

        List<Team> foundTeams = teamDao.findAllById(player.getTeamUuids());

        // check that all requested uuids are found
        Set<UUID> foundUuids = foundTeams.stream()
                .map(Team::getUuid)
                .collect(Collectors.toSet());
        Set<UUID> notfoundUuids = player.getTeamUuids()
                .stream()
                .filter(uuid -> !foundUuids.contains(uuid))
                .collect(Collectors.toSet());
        if (!notfoundUuids.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not find teamsUuids> " + notfoundUuids);

        player.setTeams(new HashSet<>(foundTeams));
        return player;
    }
    

}
