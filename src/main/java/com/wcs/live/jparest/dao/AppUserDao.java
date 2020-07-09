package com.wcs.live.jparest.dao;

import com.wcs.live.jparest.model.AppUser;

import java.util.Optional;

public interface AppUserDao extends BaseDao<AppUser> {

    Optional<AppUser> findByUsername(String username);

}
