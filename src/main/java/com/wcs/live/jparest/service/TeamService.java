package com.wcs.live.jparest.service;

import com.wcs.live.jparest.dao.BaseDao;
import com.wcs.live.jparest.dao.TeamDao;
import com.wcs.live.jparest.model.Team;
import org.springframework.stereotype.Service;

@Service
public class TeamService extends AbstractBaseService<Team> {
    private TeamDao dao;

    @Override
    public BaseDao<Team> getDao() {
        return dao;
    }
}
