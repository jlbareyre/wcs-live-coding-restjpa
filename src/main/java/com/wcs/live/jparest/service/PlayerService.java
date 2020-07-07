package com.wcs.live.jparest.service;

import com.wcs.live.jparest.dao.BaseDao;
import com.wcs.live.jparest.dao.PlayerDao;
import com.wcs.live.jparest.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends AbstractBaseService<Player> {

    @Autowired
    private PlayerDao dao;

    @Override
    public BaseDao<Player> getDao() {
        return dao;
    }

//    @Override
//    public Player create(Player player) {
//        if (player.getTeam() != null && player.getTeam().getUuid() == null) {
//            player.getTeam().setUuid(UUID.randomUUID());
//        }
//        return super.create(player);
//    }

//    @Override
//    public Player update(Player player) {
//        if (player.getTeam() != null && player.getTeam().getUuid() == null) {
//            player.getTeam().setUuid(UUID.randomUUID());
//        }
//        return super.update(player);
//    }


}
