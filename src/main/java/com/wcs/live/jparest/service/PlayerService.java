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
}
